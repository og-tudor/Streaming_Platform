** **Frecus Tudor-George \\ 324CA** **

# Proiect - Etapa 1 - POO TV
***
## Description :
Nowadays, movie or series viewing platforms such as Netflix or HBO MAX have become extremely popular both in Romania and especially worldwide.

In this regard, because we know that you have been working hard all semester to view the latest movies or episodes of your favorite series, we want to offer you the opportunity to implement a similar platform in order to understand its functionalities.

We invite you to help as soon as possible both the whole planet to continue viewing your favorite series and movies, and yourself because we want you to enjoy the holidays with your family and not working on the POO project.

### Project execution :
The input data read from the test file (which is in JSON format) will be placed into objects.

For the first stage of the project, the following will be provided initially: the list of users already registered on the platform and the list of existing videos on the platform. Of course, each of these will be identified by its own characteristics.

Actions are received sequentially ("change page" or "on page" for the first stage) and executed as they are received, the result of which will have an effect on the data on the platform at a given point in time. 
Some actions will write output to a JSON file.

When all actions are completed, the program execution is also terminated and the next set of tests are run.
***
## Implementation :

### InputData :
I made multiple classes to be able to read from the JSON inputs, these classes are populated and 
are never used, except for Credentials class.

### Page Structure :
All the pages on the platform are Singletons that store an arraylist of Pages(links) inside them.

### Movies:
In the movies package there are two Java classes, MoviesDataBase and Movie.

The DataBase is a Singleton that stores all the movies on the website, inside it there are methods like populate, search filter.

For the Filter method I constructed four Comparators for each type of filtering.

CurrentMoviesList is a class extending the moviedatabase that has the current movie list for the user after the banned countries are excluded and/or after a filter or search, etc.

Each Movie has the same properties as the ones from input.


### Users :
In the users package there are two Java classes, UsersDataBase and User.

The DataBase is a Singleton that stores all the users on the website, inside it there are methods like findUser, isertUser, etc.

Each user has methods to verify a purchase, watch, like, or rate on a movie and also:
<ul>
    <li>Credentials</li>
    <li>Number of available Tokens</li>
    <li>Number of free premium movies available</li>
    <li>A list of purchased Movies</li>
    <li>A list of watched Movies</li>
    <li>A list of liked Movies</li>
    <li>A list of rated Movies</li>
</ul>

### Actions :
I made two boolean variables, printOut and printError to determine if there is output to write and if it is an error or not.

I made all the decision-making in the Main class, the flow of the program is as fallows:
<ul>
    <li>Input is read from the json file and transferred to the Input class</li>
    <li>All the databases are initialised and other useful variables</li>
    <li>Then I iterate through the lists of actions</li>
    <ul>
        <li>If the action is type "change page", then I verify that the user can go to that page and perform the action.</li>
        <li>If the action is type "on page", I verify that the user is on the right place to perform the action and the action is performmed.</li>
        <li>Then i make copies of the current data and add it to the output node, it an error occured the program writes the error.</li>
    </ul>
<li>Finally I write the output to the JSON file</li>
</ul>

## Personal Comments :
The explications on the HomeWork Page weren't very useful, it wasn't properly explained when and how to print the output, sometimes the explications
would not be in agreement ot the answers on the forum(ex: It was written on OCW that filtering is done first by rating and then by duration but on the forum there is the exact opposite answer.)
Besides this I think it was a good homework.

For improvement, I could have used a Design Pattern to remember which action was previously done instead of having a boolean value for "filter", for example.

I could have also modularized the code in main but I didn't have the time to.

I made a custom serializer for Movie class to display double decimal places for rating but for the life of me I still couldn't get it to show 2 decimal places.
