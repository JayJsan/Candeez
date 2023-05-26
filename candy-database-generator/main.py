import csv

def extract_product_data(text):
    lines = text.split('/')
    price = lines[0].strip()
    category = lines[1].strip()
    name = lines[2].strip()
    description = lines[3].strip()
    return price, category, name, description

def write_to_csv(data, filename):
    with open(filename, 'w', newline='') as csvfile:
        writer = csv.writer(csvfile)
        writer.writerow(['price', 'image_uri','category','view_count','is_favourite','cart_quantity','name', 'description'])
        writer.writerow(data)
    print(f"CSV file '{filename}' generated successfully.")

# Take input from the user
input_text = input("Enter the product details (price, category, name, description) seperated by a forward slash:\n")

# Extract the product data
product_price, product_category, product_name, product_description = extract_product_data(input_text)

# Specify the filename for the CSV file
filename = 'candeez_database.csv'

# Write the product data to a CSV file
write_to_csv([product_price, "", product_category, 0, 0,product_name, product_description], filename)