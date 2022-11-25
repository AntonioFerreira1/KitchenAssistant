package com.example.kitchenassistant.ui

data class Product(
    val title: String, val quantity: Int, val unit: Unit
)

enum class Unit {
    GRAMS, KILOGRAM, UNIT, PACKAGE, LITERS, MILLILITERS
}

data class Recipe(
    val title: String,
    val category: String,
    val duration: Int,
    val ingredients: String,
    val steps: String,
    val img: String

)

val allRecipes = arrayOf(

    Recipe(
        "Lasagne", "Italian Food", 120,
        "2 tablespoons olive oil\n1 sweet onion, diced\n2 garlic cloves, minced\n1 pound ground beef\n1 teaspoon dried oregano\n1½ cups marinara sauce\n2 pounds ravioli\n1½ cups ricotta cheese\n2 cups shredded mozzarella cheese",
        "1. Preheat the oven to 375°F\n\n2. In a large skillet, heat the olive oil over medium heat. Add the onion and sauté until translucent, 4 to 5 minutes. Add the garlic and cook until fragrant, 1 minute more.  \n\n3. Add the ground beef and sauté until fully cooked, 6 to 8 minutes. Season with the salt, pepper and oregano.  \n\n4. Stir in the marinara sauce and bring to a simmer over medium-low heat. Simmer until the sauce thickens slightly and has good flavor, 20 minutes.  \n\n5. While the sauce cooks, bring a large pot of salted water to a boil. When it comes to a rolling boil, add the ravioli and cook according to the instructions on the package. Drain well.  \n\n6. To build the lasagna, place a layer of ravioli in the base of the pan. Ladle a quarter of the sauce over the ravioli, then top with ½ cup ricotta cheese, spread into a layer. Sprinkle with a ¼ cup mozzarella.  \n\n7. Repeat the layering process two more times. For the fourth and final layer, add one more layer of ravioli, sauce and mozzarella (no ricotta this time)."
        , "ravioli"
    ),
    Recipe(
        "Butter Chicken", "Indian Food", 50,
        "2 lb boneless, skinless chicken breast\nsalt\npepper\n2 teaspoons chili powder\n½ teaspoon turmeric\n6 tablespoons butter\n1 ½ cups yellow onion(225 g)",
        "1 - In a large bowl, season the chicken breast with salt, pepper, 1 teaspoon of chili powder, and the teaspoon of turmeric. Let sit for 15 minutes to marinate.\n\n2 - Melt 2 tablespoons of butter in a large pot over medium heat. Brown the chicken, then remove from the pot.\n\n3 - Melt another 2 tablespoons of butter in the pot, then add the onion, remaining teaspoon of chili powder, the cumin, ginger, garlic, cayenne, cinnamon, salt and pepper. Cook until fragrant.\n\n4 - Add the tomato sauce and bring to a simmer.\n\n5 - Add the water and cream and return to a simmer.\n\n6 - Return the chicken to the pot, cover, and simmer for another 10-15 minutes.\n\n7 - Stir in the last 2 tablespoons of butter and season with more salt and pepper to taste.\n\n8 - Serve the chicken over rice and garnish with cilantro.\n\n9 - Enjoy!",
        "chicken"
    ),
    Recipe(
        "Guacamole", "Mexican Food", 10,
        "1 large ripe tomato\n3 avocados, very ripe but not bruised\njuice 1 large lime\nhandful coriander, leaves and stalks chopped, plus a few leaves, roughly chopped, to serve\n1 small red onion, finely chopped\n1 chilli, red or green, deseeded and finely chopped\ntortilla chips, to serve",
        "1 - Use a large knife to pulverise 1 large ripe tomato to a pulp on a board, then tip into a bowl.\n\n2 - Halve and stone the 3 avocados (saving a stone) and use a spoon to scoop out the flesh into the bowl with the tomato.\n\n3 - Tip the juice of 1 large lime, a handful of roughly chopped coriander, 1 finely chopped small red onion and 1 deseeded and finely chopped red or green chilli into the bowl, then season with salt and pepper.\n\n4 - Use a whisk to roughly mash everything together. If not serving straight away, cover with cling film and chill until needed.",
        "guacamole"
    )
)







