# COMPSYS 302 Project 2 Team 20

---

## ![Candeez Logo](/readme-resources/Candeez_Logo.PNG "Candeez Logo")

#### Candeez is an Android Application made on Android Studio using Java. It is an application created to showcase and sell sweets or snacks. The items on Candeez were implemented using SQLite.

#### This was a University Project for COMPSYS 302 where we had to design an application to showcase a product type of our own choosing according to a set of specifications and requirements.

#### Developed by `Benson Cho` and `Josef Santos`

Initial Design Document:
https://docs.google.com/document/d/1Fg8QJMB5KvNC1VupsUTpzmRiNFF9sdRBnZac55ALOE4/edit?usp=sharing

Wireframe Prototype:  
https://www.figma.com/proto/zfG2Bn3AtoYYxg2nj4IXZv/302-Java-App-Project-2?type=design&node-id=167-243&scaling=min-zoom&page-id=167%3A243&starting-point-node-id=167%3A244

---

## Installation

To run the application, use Android Studio to emulate it or use debugging mode on your phone to run it on your Android device.

## Features

### Main Homepage

This is the main landing page of Candeez. Here you can view 13 categories of snacks and goodies. Clicking a category will take you to the ListActivity where you can browse items. You also can view the categories in grid form.

You can also see the three best-selling items and the three most viewed items on this page. If an item interests you, press the heart button to save it to your favourites list.

At the bottom is the navigation bar where you navigate to the ListActivity, FavouritesActivity, and CartActivity.

<br/>
<p float="left">
    <img src="readme-resources/MainActivity_V2.jpg" width="270" height="600">
    <img src="readme-resources/MainActivity_GridCategory.jpg" width="270" height="600">
</p>

### Browse Items

This is the ListActivity, where you can browse through several items depending on your filters and sort through the list using the sort button. At the top is the search card, where you can search for a specific item. You may add an item to the cart and favourite an item on this page.

Clicking on any item will take you to the DetailsActivity, showing more details on the related product.

<br/>
<p float="left">
    <img src="readme-resources/ListActivity_V2.jpg" width="270" height="600">
    <img src="readme-resources/ListActivity_Sort.jpg" width="270" height="600">
</p>

### Item Details

Once an item is clicked, it will take you to the DetailsActivity, which will display more product information. You may view different pictures in this view, favourite the item, or add to the cart.

Scrolling down below will show a list of related items.

<br/>
<img src="readme-resources/DetailsActivity_V2.jpg" width="270" height="600">

### Favourites

Clicking on the heart icon in the navigation bar will lead you to the FavouritesActivity. Here you will see your favourited items. Clicking on the item will bring you to the details page.

<br/>
<p float="left">
    <img src="readme-resources/Favourites_Filled_V2.jpg" width="270" height="600">
    <img src="readme-resources/Favourites_Empty.jpg" width="270" height="600">
</p>

### Cart

Clicking on the cart icon in the navigation bar will bring you to the cart activity. Here you will be able to see the items you have added to your cart and can either increase or decrease the amount of each item. Tapping on the "X" will remove the item from the cart.
<br/>

<p float="left">
<img src="readme-resources/Cart_Filled_V2.jpg" width="270" height="600">
<img src="readme-resources/Cart_Empty.jpg" width="270" height="600">
</p>

## Animations and Transitions

<p float="left">
    <img src="readme-resources/Activity_Switching_AdobeExpress.gif" width="202" height="450">
    <img src="readme-resources/Category_Swipe_AdobeExpress.gif" width="202" height="450">
    <img src="readme-resources/Filter_Swipe_AdobeExpress.gif" width="202" height="450">
</p>

## Technologies Used

- `Android Studio` : Used to handle compiling, designing, emulating and among many other things.
- `Java` : The language used to code the application.
- `SQLite` : Used to handle the database for Candeez
- `Python` : Used to generate the database for Candeez (Item specifications, Images).
