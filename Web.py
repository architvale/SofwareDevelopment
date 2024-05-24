import requests
from bs4 import BeautifulSoup
import csv
import urllib3

# Disable SSL certificate verification (not recommended for production)
urllib3.disable_warnings(urllib3.exceptions.InsecureRequestWarning)

def scrape_product_info():
    url = "https://www.ebay.com/sch/i.html?_nkw="
    search_term = "laptops"  # Change this to the desired search term
    headers = {"User-Agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/96.0.4664.110 Safari/537.36"}
    
    try:
        response = requests.get(url + search_term, headers=headers, verify=False)
        response.raise_for_status()  # Raise an exception for 4xx or 5xx status codes
        if response.status_code == 200:
            soup = BeautifulSoup(response.content, 'html.parser')
            product_containers = soup.select(".s-item")

            with open('product_information.csv', 'w', newline='', encoding='utf-8') as csvfile:
                writer = csv.writer(csvfile)
                writer.writerow(['Rank', 'Name', 'Price'])

                rank = 1
                for container in product_containers:
                    name = container.select_one(".s-item__title").get_text(strip=True)
                    price = container.select_one(".s-item__price").get_text(strip=True)
                    
                    writer.writerow([rank, name, price])
                    rank += 1

            print("Product information extracted and saved to product_information.csv")
    except requests.RequestException as e:
        print("Failed to fetch data from the website:", e)

if __name__ == "__main__":
    scrape_product_info()
