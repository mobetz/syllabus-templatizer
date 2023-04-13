

import ratingFinder from 'book-ratings'
let ratings = ratingFinder.get_book_ratings();


for ( let book of ratings ) {
	console.log("The next title is: " + book.Title);
}