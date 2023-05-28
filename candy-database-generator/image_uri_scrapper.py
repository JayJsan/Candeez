import requests
from bs4 import BeautifulSoup
import urllib.parse

def scrape_images(category, num_images):
    category = urllib.parse.quote_plus(category)
    url = f"https://www.google.com/search?q={category}&tbm=isch"
    headers = {
        "User-Agent": "Mozilla/5.0 (Windows NT 10.0;Win64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.3"
    }
    
    response = requests.get(url, headers=headers)
    soup = BeautifulSoup(response.content, "html.parser")
    
    image_links = []
    for img in soup.find_all("img"):
        image_link = img.get("src")
        if image_link and image_link.startswith("http"):
            image_links.append(image_link)
            if len(image_links) == num_images:
                break
    
    return image_links

def group_links(links, group_size):
    return [links[i:i+group_size] for i in range(0, len(links), group_size)]

# Example usage
category = input("Enter a category keyword: ")
num_images = 3
group_size = 1

image_links = scrape_images(category, num_images)
image_groups = group_links(image_links, group_size)



txt = "\"{0}, {1}, {2}\""
print(txt.format(image_links[0], image_links[1], image_links[2]))