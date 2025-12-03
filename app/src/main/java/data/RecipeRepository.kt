package com.example.moodkitchen.data
import com.example.moodkitchen.R
import com.example.moodkitchen.model.Recipe

object RecipeRepository {
    private val recipes = mapOf(
        "Happy" to listOf(
            Recipe(
                "Berry Smoothie",
                "Sweet and refreshing start to your day.",
                ingredients = listOf(
                    "1 cup strawberries",
                    "1 banana",
                    "1/2 cup yogurt",
                    "1 cup milk"
                ),
                directions = "Blend all ingredients until smooth and serve chilled.",
                imageRes = R.drawable.smoothie
            ),
            Recipe(
                "Mango Salsa Tacos",
                "Fresh, bright, and colorful.",
                ingredients = listOf("2 mangoes", "1/2 red onion", "1 lime", "Taco shells"),
                directions = "Mix diced mango, onion, and lime juice. Fill taco shells and enjoy.",
                imageRes = R.drawable.mangosalsa
            ),
            Recipe(
                "Ice Cream Sundae",
                "A delightful way to extend the good vibes.",
                ingredients = listOf(
                    "3 scoops vanilla ice cream",
                    "1/2 cup hot fudge or caramel sauce",
                    "1/4 cup of whipped cream",
                    "2 cherries",
                    "2 tablespoons chopped nuts"
                ),
                directions = "Warm your sauce. Place scoops of ice cream in a bowl. Pour warm sauce over ice cream. Top with cream, nuts and a cherry. Serve immediately.",
                imageRes = R.drawable.sundae
            ),
            Recipe(
                "Sparkling Lemonade",
                "Fizzy, fun, and full of sunshine.",
                ingredients = listOf(
                    "1 cup fresh lemon juice",
                    "1/2 cup of sugar",
                    "4 cups sparkling water",
                    "Ice cubes",
                    "Lemon slices for garnish"
                ),
                directions = "Mix lemon juice and sugar in a pitcher until sugar dissolves. Add sparkling water adn stir gently. Fill glasses with ice and pour lemonade over. Garnish with lemon slices. Serve immediately.",
                imageRes = R.drawable.lemonade
            ),
            Recipe(
                "Beef Tenderloin",
                "A hearty, feel-good meal that turns joy into flavor.",
                ingredients = listOf(
                    "2 lbs beef tenderloin",
                    "3 tablespoons olive oil",
                    "4 cloves minced garlic",
                    "2 tablespoons fresh thyme",
                    "1 teaspoon salt",
                    "1 teaspoon black pepper"
                ),
                directions = "Preheat oven to 425 degrees. Mix olive oil, garlic, thyme, salt, and pepper. Rub mixture all over beef tenderloin. Place on roasting pan and cook for 25-30 minutes for medium-rare or until desired doneness. Let rest 10 minutes before slicing. Serve warm.",
                imageRes = R.drawable.beeftenderloin
            ),
            Recipe(
                "Peach Cobbler",
                "A joyful treat that lifts the spirits.",
                ingredients = listOf(
                    "6 fresh or 1 can peaches",
                    "1 cup sugar",
                    "1 cup flour",
                    "1 cup milk",
                    "1 stick melted butter",
                    "2 teaspoons baking powder",
                    "1 teaspoon vanilla extract",
                    "1/2 teaspoon cinnamon"
                ),
                directions = "Preheat oven to 350 degrees. Place peaches in a greased baking dish. Mix flour, sugar, milk, baking powder, vanilla, and cinnamon. Pour batter over peaches. Drizzle melted butter on top. Bake for 45-50 minutes until golden brown and bubbly. Serve warm.",
                imageRes = R.drawable.peachcobbler
            ),
        ),
        "Tired" to listOf(
            Recipe(
                "Avocado Toast",
                "Quick, energizing, and easy to make.",
                ingredients = listOf(
                    "2 slices of bread",
                    "1 ripe avocado",
                    "1 tablespoon lemon juice",
                    "Salt to taste",
                    "Red pepper flakes"
                ),
                directions = "Toast bread until golden brown and crispy. Mash avocado in a bowl with lemon juice, salt, and pepper. Spread mixture evenly on toast. Top with red pepper flakes. Serve immediately.",
                imageRes = R.drawable.avacadotoast
            ),
            Recipe(
                "Oat Latte",
                "Comfort in a cup.",
                ingredients = listOf(
                    "2 shots espresso",
                    "1 cup oat milk",
                    "1 tablespoon maple syrup",
                    "1/2 teaspoon vanilla extract",
                    "Cinnamon for dusting"
                ),
                directions = "Brew espresso shots and pour into mug. Heat oat milk in a saucepan until steaming but not boiling. Froth oat milk using a frother or whisk. Add maple syrup and vanilla to espresso. " + "Pour froth oat milk over espresso. Dust with cinnamon on top. Serve hot.",
                imageRes = R.drawable.oatlatte
            ),
            Recipe(
                "Chicken Noodle Soup",
                "Restorative and easy to digest.",
                ingredients = listOf(
                    "2 cups cooked shredded chicken",
                    "8 cups chicken broth",
                    "2 cups egg noodles",
                    "3 carrots sliced",
                    "3 celery stalks chopped",
                    "3 cloves minced garlic",
                    "2 tablespoons olive oil",
                    "1 teaspoon thyme",
                    "Salt and pepper to taste",
                    "Fresh parsley for garnish"
                ),
                directions = "Heat olive oil in a large pot. Saute carrots and celery till softened. Add garlic and cook for 1 minute. Pour in chicken broth and bring to a boil. Add noodles and thyme, cook for 8-10 minutes. Stir in shredded chicken and season with salt and pepper. Simmer for 5 more minutes. Garnish with fresh parsley and serve hot.",
                imageRes = R.drawable.chickennoodlesoup
            ),
            Recipe(
                "Mashed Potatoes with Butter",
                "Velvety smooth and made to soothe.",
                ingredients = listOf(
                    "4 large russet potatoes peeled and cubed",
                    "1/2 cup butter",
                    "1/2 cup milk",
                    "1/4 cup sour cream",
                    "Salt to taste",
                    "Black pepper to taste"
                ),
                directions = "Boil potatoes in salted water until tender, about 15-20 minutes. Drain well, Mash potatoes with a potato masher. Add butter, milk, and sour cream. Mix until smooth and creamy. Season with salt and pepper. Serve hot.",
                imageRes = R.drawable.mashedpotatoes
            ),
            Recipe(
                "Grilled Cheese Sandwich",
                "Simple, steady, and just right.",
                ingredients = listOf(
                    "2 slices bread",
                    "2 slices cheddar cheese",
                    "2 tablespoons butter",
                    "Pinch of salt"
                ),
                directions = "Butter one side of each bread slice. Place one slice butter-side down in a heated skillet. Add cheese slices. Top with second bread slice butter-side up. Cook on medium heat for 2-3 minutes until golden brown. Flip and cook other side for 2-3 minutes. Serve hot and melty.",
                imageRes = R.drawable.grilledcheese
            ),
            Recipe(
                "Rice Porridge",
                "Mild flavor and kind to tired stomachs.",
                ingredients = listOf(
                    "1 cup rice",
                    "6 cups water or chicken broth",
                    "1 teaspoon salt",
                    "2 tablespoons ginger minced",
                    "1 tablespoon sesame oil",
                    "Soy sauce to taste",
                    "Soft boiled egg for topping"
                ),
                directions = "Rinse rice thoroughly. Bring water or broth to a boil in a large pot. Add rice, salt, and ginger. Reduce heat to low and simmer for 45-60 minutes, stirring occasionally until rice breaks down and becomes creamy. Add more liquid if needed. Drizzle with sesame oil and soy sauce. Top with soft boiled egg. Serve hot.",
                imageRes = R.drawable.riceporridge
            ),
        ),
        "Cozy" to listOf(
            Recipe(
                "Tomato Soup",
                "Warm and nostalgic.",
                ingredients = listOf(
                    "4 cups tomatoes",
                    "2 cups vegetable broth",
                    "1 onion diced",
                    "2 cloves garlic",
                    "Salt and pepper"
                ),
                directions = "Saute onion and garlic. Add tomatoes and broth. Simmer for 20 minutes. Blend until smooth. Season and serve hot.",
                imageRes = R.drawable.tomatosoup
            ),
            Recipe(
                name = "Mac and Cheese",
                description = "Soft, creamy comfort.",
                ingredients = listOf(
                    "2 cups elbow macaroni",
                    "2 cups cheddar cheese",
                    "2 cups milk",
                    "3 tablespoons butter",
                    "3 tablespoons flour",
                    "Salt and Pepper to taste"
                ),
                directions = "Cook macaroni according to package directions and drain. In a pot, melt butter and whisk in flour. Gradually add milk, stirring constantly. Add cheese and stir until melted. Mix in cooked macaroni. Season with salt and pepper. Serve warm.",
                imageRes = R.drawable.macncheese
            ),
            Recipe(
                "Caprese Salad",
                "Pure and light.",
                ingredients = listOf(
                    "2 large tomatoes sliced",
                    "8 oz fresh mozzarella sliced",
                    "Fresh basil leaves",
                    "3 tablespoons olive oil",
                    "2 tablespoons balsamic vinegar",
                    "Salt and pepper to taste"
                ),
                directions = "Arrange tomato and mozzarella slices on a plate, alternating them. Tuck basil leaves between slices. Drizzle with olive oil and balsamic vinegar. Season with salt and pepper. Serve immediately.",
                imageRes = R.drawable.capresesalad
            ),
            Recipe(
                "Banana Pudding",
                "Mellow and gentle.",
                ingredients = listOf(
                    "4 ripe bananas sliced",
                    "2 cups vanilla pudding",
                    "1 box vanilla wafers",
                    "2 cups whipped cream",
                    "1 teaspoon vanilla extract"
                ),
                directions = "Layer vanilla wafers in a dish. Add a layer of banana slices. Spread vanilla pudding over bananas. Repeat layers. Top with whipped cream. Chill for 2 hours before serving.",
                imageRes = R.drawable.bananapudding
            ),
            Recipe(
                "Pancakes",
                "Fluffy and tender.",
                ingredients = listOf(
                    "2 cups flour",
                    "2 tablespoons sugar",
                    "2 teaspoons baking powder",
                    "1/2 teaspoon salt",
                    "2 eggs",
                    "1 3/4 cup milk",
                    "1/4 cup melted butter",
                    "Maple syrup for serving"
                ),
                directions = "Mix flour, sugar, baking powder, and salt. In another bowl, whisk eggs, milk, and melted butter. Combine wet and dry ingredients. Heat a griddle and pour 1/4 cup batter for each pancake. Cook until bubble form, then flip. Cook until golden. Serve with maple syrup.",
                imageRes = R.drawable.pancakes
            ),
            Recipe(
                "Baked Beans",
                "A quiet, wholesome dish that satisfies the heart.",
                ingredients = listOf(
                    "2 cans navy beans drained",
                    "1/2 cup ketchup",
                    "1/4 cup brown sugar",
                    "2 tablespoons molasses",
                    "1 teaspoon mustard",
                    "4 slices bacon chopped"
                ),
                directions = "Preheat oven to 350 degrees. Cook bacon in a skillet until bacon is crispy. In a baking dish, combine beans, ketchup, brown sugar, molasses, mustard, and bacon. Mix well. Bake for 45 minutes until bubbly. Serves warm.",
                imageRes = R.drawable.bakedbeans
            ),
        ),
        "Stressed" to listOf(
            Recipe(
                "Green Tea",
                "Relax and breathe.",
                ingredients = listOf(
                    "1 green tea bag",
                    "1 cup hot water",
                    "1 teaspoon honey",
                    "Lemon slice optional"
                ),
                directions = "Boil water and let cool slightly to 175 degrees. Place tea bag in cup. Pour hot water over tea bag. Steep for 2-3 minutes. Remove tea bag. Add honey and lemon if desired. Sip slowly and relax.",
                imageRes = R.drawable.greentea
            ),
            Recipe(
                "Dark Chocolate Muffin",
                "A small indulgence to lift your mood.",
                ingredients = listOf(
                    "1 1/2 cups flour",
                    "1/2 cup cocoa powder",
                    "1 cup sugar",
                    "1 teaspoon baking soda",
                    "1/2 teaspoon salt",
                    "1 cup milk",
                    "1/3 cup vegetable oil",
                    "1 egg",
                    "1 teaspoon vanilla",
                    "1/2 cup dark chocolate chips"
                ),
                directions = "Preheat oven to 350 degrees. Line muffin tin with paper liners. Mix flour, cocoa, sugar, baking soda, and salt. In another bowl, whisk milk, oil, egg, and vanilla. Combine wet and dry ingredients. Fold in chocolate chips. Fill muffin cups 2/3 full. Bake for 18-20 minutes. Cool and enjoy.",
                imageRes = R.drawable.darkchocolatemuffins
            ),
            Recipe(
                "Hot Chocolate with Marshmallows",
                "Sweet, toasty and safe.",
                ingredients = listOf(
                    "2 cups of milk",
                    "2 tablespoons cocoa powder",
                    "2 tablespoons sugar",
                    "1/4 teaspoons vanilla extract",
                    "Marshmallows for topping",
                    "Whipped cream optional"
                ),
                directions = "Heat milk in a saucepan over medium heat. Whisk in cocoa powder and sugar until dissolved. Stir in vanilla. Heat until steaming but not boiling. Pour into mugs. Top with marshmallows and whipped cream. Serve immediately.",
                imageRes = R.drawable.hotchocolate
            ),
            Recipe(
                "Oatmeal with Honey",
                "Comforts the mind and eases tension.",
                ingredients = listOf(
                    "1 cup rolled oats",
                    "2 cups water or milk",
                    "2 tablespoons honey",
                    "1/4 teaspoon cinnamon",
                    "Pinch of salt",
                    "Fresh berries for topping",
                    "Sliced almonds optional"
                ),
                directions = "Bring water or milk to a boil. Add oats and salt. Reduce heat and simmer for 5 minutes, stirring occasionally. Remove from heat. Stir in honey and cinnamon. Top with fresh berries and almonds. Serve warm.",
                imageRes = R.drawable.oatmeal
            ),
            Recipe(
                "Salmon Teriyaki",
                "A warm, steady bite for frazzled nerves.",
                ingredients = listOf(
                    "2 salmon fillets",
                    "1/4 cup teriyaki sauce",
                    "1 tablespoon honey",
                    "1 teaspoon sesame oil",
                    "2 cloves minced garlic",
                    "Sesame seeds for garnish",
                    "Green onions sliced"
                ),
                directions = "Mix teriyaki sauce, honey, sesame oil, and garlic. Marinate salmon for 15 minutes. Heat a skillet over medium-high heat. Cook salmon skin side down for 4-5 minutes. Flip and cook for 3-4 minutes more. Brush with remaining marinade. Garnish with sesame seeds and green onions. Serve with rice.",
                imageRes = R.drawable.salmonteriyaki
            ),
            Recipe(
                "Scrambled Eggs",
                "A humble, familiar dish that steadies the senses.",
                ingredients = listOf(
                    "4 eggs",
                    "2 tablespoons milk",
                    "2 tablespoons butter",
                    "Salt and pepper to taste"
                ),
                directions = "Crack eggs into a bowl. Add milk, salt, and pepper. Whisk until well combined. Heat butter in a non-stick pan over medium-high heat. Pour in egg mixture. Gently stir with a spatula, pushing eggs from edges to center. Cook until just set but still creamy. Remove from heat. Serve immediately.",
                imageRes = R.drawable.scrambledeggs
            )
        )
    )

    fun getRecipesForMood(mood: String): List<Recipe> {
        return recipes[mood] ?: emptyList()
    }

    fun getRecipesForMoodAndIngredients(mood: String, userIngredients: List<String>): List<Recipe> {
        val moodRecipes = recipes[mood] ?: emptyList()

        // If no ingredients specified, return all mood recipes
        if (userIngredients.isEmpty()) {
            return moodRecipes
        }

        // Filter recipes that contain at least one of the user's ingredients
        return moodRecipes.filter { recipe ->
            recipe.ingredients.any { recipeIngredient ->
                userIngredients.any { userIngredient ->
                    recipeIngredient.contains(userIngredient, ignoreCase = true)
                }
            }
        }
    }
}

    // Filter recipes that contain at least one of the user's ingredients



