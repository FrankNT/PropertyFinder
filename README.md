# PropertyFinder

Create an endless scrolling view, resembling the below example. Add buttons to change the sort-order.
Sorting can be done by price or by number of bedrooms, in either ascending or descending order.
 
Read the data using the simple json api found here (it should be self-explanatory):
 
https://www.propertyfinder.ae/mobileapi?page=[page number]&order=[pd|pd|ba|bd]
page: integer, starting with 0 for the first page
order: pd=price, decending pa=price ascending, bd=price descending, ba=price ascending
