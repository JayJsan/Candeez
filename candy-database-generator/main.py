import csv
import os

def extract_product_data(text):
    lines = text.split('/')
    name = lines[0].strip()
    price = lines[1].strip()
    description = lines[2].strip()
    category = lines[3].strip()
    return price, category, name, description

def write_to_csv(data, filename):
    file_exists = os.path.isfile(filename)
    with open(filename, 'a', newline='') as csvfile:
        writer = csv.writer(csvfile)
        if not file_exists:
            writer.writerow(['price', 'image_uri','category','view_count','is_favourite','cart_quantity','name', 'description'])
        writer.writerow(data)
    print(f"CSV file '{filename}' generated/edited successfully.")

while True:
    # Take input from the user
    input_text = input("Enter the product details (name / price / description / category) seperated by a forward slash:\n")

    if input_text.lower() == 'q':
        break
    if input_text == '':
        continue

    # Extract the product data
    product_price, product_category, product_name, product_description = extract_product_data(input_text)

    # Specify the filename for the CSV file
    filename = 'candeez_database.csv'

    # Write the product data to a CSV file
    write_to_csv([product_price, "", product_category, 0, 0, 0, product_name, product_description], filename)