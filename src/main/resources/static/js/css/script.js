$(document).ready(function() {
    // Handle form submission for adding a new book
    $('#addBookForm').on('submit', function(event) {
        event.preventDefault(); // Prevent default form submission

        // Get form data
        var bookData = {
            title: $('#title').val(),
            author: $('#author').val(),
            isbn: $('#isbn').val(),
            genre: $('#genre').val(),
            status: $('#status').val(),
            copies: $('#copies').val()
        };

        // Send POST request to add the book
        $.ajax({
            url: '/books/add',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(bookData),
            success: function(response) {
                alert(response); // Display success message
                $('#addBookForm')[0].reset(); // Reset form
                fetchAvailableBooks(); // Refresh the list of available books
            },
            error: function(xhr, status, error) {
                alert('Error: ' + xhr.responseText); // Display error message
            }
        });
    });

    // Function to fetch and display available books
    function fetchAvailableBooks() {
        $.ajax({
            url: '/books/available',
            type: 'GET',
            success: function(books) {
                $('#availableBooksList').empty(); // Clear existing list
                books.forEach(function(book) {
                    $('#availableBooksList').append('<li>' + book.title + ' by ' + book.author + ' (ISBN: ' + book.isbn + ')</li>');
                });
            },
            error: function(xhr, status, error) {
                alert('Error: ' + xhr.responseText);
            }
        });
    }

    // Function to fetch and display rented books
    function fetchRentedBooks() {
        $.ajax({
            url: '/books/rented',
            type: 'GET',
            success: function(books) {
                $('#rentedBooksList').empty(); // Clear existing list
                books.forEach(function(book) {
                    $('#rentedBooksList').append('<li>' + book.title + ' by ' + book.author + ' (ISBN: ' + book.isbn + ')</li>');
                });
            },
            error: function(xhr, status, error) {
                alert('Error: ' + xhr.responseText);
            }
        });
    }

    // Initial fetch of available and rented books
    fetchAvailableBooks();
    fetchRentedBooks();
});
