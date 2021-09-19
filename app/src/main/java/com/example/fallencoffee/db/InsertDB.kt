package com.example.fallencoffee.db

import android.content.ContentValues
import android.os.Build
import androidx.annotation.RequiresApi
import org.ktorm.dsl.insert
import org.ktorm.dsl.insertAndGenerateKey
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime


class InsertDB {
    private val cupdb = CupDB().connection()

    @RequiresApi(Build.VERSION_CODES.O)
    fun Stores.insert(values: ContentValues) {
        cupdb.insert(Stores) {
            set( it.store_ID, values.getAsInteger("store_ID") )
            set( it.store_name, values.getAsString("store_name") )
            set( it.store_location, values.getAsString("store_location") )
            set( it.references, values.getAsString("references") )
            set( it.fundation, LocalDate.parse(values.getAsString("fundation")) )
            set( it.s_image, values.getAsByteArray("s_image") )
            set( it.l_image, values.getAsByteArray("l_image") )
            set( it.QRcode_image, values.getAsByteArray("QRcode_image") )
        }
    }

    fun Tables_tmp.insert(values: ContentValues) {
        cupdb.insert(Tables_tmp) {
            set( it.table_ID, values.getAsInteger("table_ID") )
            set( it.store_ID, values.getAsInteger("store_ID") )
            set( it.quantity_users, values.getAsInteger("quantity_users") )
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun Users.insert(values: ContentValues) {
        cupdb.insert(Users) {
            set( it.user_ID, values.getAsInteger("user_ID") )
            set( it.username, values.getAsString("username") )
            set( it.email, values.getAsString("email") )
            set( it.password, values.getAsString("password") )
            set( it.date_account, LocalDate.parse(values.getAsString("date_account")) )
            set( it.table_ID, values.getAsInteger("table_ID") )
            set( it.s_image, values.getAsByteArray("s_image") )
            set( it.m_image, values.getAsByteArray("m_image") )
            set( it.QRcode_image, values.getAsByteArray("QRcode_image") )
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun Orders.insert(values: ContentValues) {
        cupdb.insert(Orders) {
            set( it.order_ID, values.getAsInteger("order_ID") )
            set( it.order_date, LocalDateTime.parse(values.getAsString("order_date")) )
            set( it.user_ID, values.getAsInteger("user_ID") )
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun Contacts.insert(values: ContentValues) {
        cupdb.insert(Contacts) {
            set( it.contact_ID, values.getAsInteger("contact_ID") )
            set( it.user_ID, values.getAsInteger("user_ID") )
            set( it.contact_category, values.getAsString("contact_category") )
            set( it.is_selected, values.getAsBoolean("is_selected") )
            set( it.date_added, LocalDate.parse(values.getAsString("date_added")) )
        }
    }

    fun Users_Contacts.insert(values: ContentValues) {
        cupdb.insert(Users_Contacts) {
            set( it.user_ID, values.getAsInteger("user_ID") )
            set( it.contact_ID, values.getAsInteger("contact_ID") )
        }
    }

    fun ContactCategories.insert(values: ContentValues) {
        cupdb.insert(ContactCategories) {
            set( it.contact_category, values.getAsString("contact_category") )
            set( it.discount, values.getAsDouble("discount") )
        }
    }

    fun SearchTags.insert(values: ContentValues) {
        cupdb.insertAndGenerateKey(SearchTags) {
            set( it.tag_name, values.getAsString("tag_name") )
        }
    }

    fun Contacts_Tags.insert(values: ContentValues) {
        cupdb.insert(Contacts_Tags) {
            set( it.contact_ID, values.getAsInteger("contact_ID") )
            set( it.tag_ID, values.getAsInteger("tag_ID"))
        }
    }

    fun ComboPacks.insert(values: ContentValues) {
        cupdb.insert(ComboPacks) {
            set( it.combo_ID, values.getAsInteger("combo_ID") )
            set( it.combo_name, values.getAsString("combo_name") )
            set( it.temperature, values.getAsString("temperature") )
        }
    }

    fun Users_Combos.insert(values: ContentValues) {
        cupdb.insert(Users_Combos) {
            set( it.user_ID, values.getAsInteger("user_ID") )
            set( it.combo_ID, values.getAsInteger("combo_ID") )
        }
    }

    fun Cravings.insert(values: ContentValues) {
        cupdb.insert(Cravings) {
            set( it.craving_ID, values.getAsInteger("craving_ID") )
            set( it.craving_type, values.getAsString("craving_type") )
            set( it.craving_name, values.getAsString("craving_name") )
            set( it.craving_quantity, values.getAsString("craving_quantity") )
        }
    }

    fun Combos_Cravings.insert(values: ContentValues) {
        cupdb.insert(Combos_Cravings) {
            set( it.combo_ID, values.getAsInteger("combo_ID") )
            set( it.craving_ID, values.getAsInteger("craving_ID") )
        }
    }

    fun HealthyAlternatives.insert(values: ContentValues) {
        cupdb.insert(HealthyAlternatives) {
            set( it.ha_ID, values.getAsInteger("ha_ID") )
            set( it.food_alternative, values.getAsString("food_alternative") )
        }
    }

    fun Combos_HA.insert(values: ContentValues) {
        cupdb.insert(Combos_HA) {
            set( it.combo_ID, values.getAsInteger("combo_ID") )
            set( it.ha_ID, values.getAsInteger("ha_ID") )
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun Products.insert(values: ContentValues) {
        cupdb.insert(Products) {
            set( it.product_ID, values.getAsInteger("product_ID") )
            set( it.product_name, values.getAsString("product_name") )
            set( it.taste, values.getAsString("taste") )
            set( it.price, values.getAsDouble("price") )
            set( it.s_image, values.getAsByteArray("s_image") )
            set( it.l_image, values.getAsByteArray("l_image") )
            set( it.prep_time, LocalTime.parse(values.getAsString("prep_time")) )
        }
    }

    fun Combos_Products.insert(values: ContentValues) {
        cupdb.insert(Combos_Products) {
            set( it.combo_ID, values.getAsInteger("combo_ID") )
            set( it.product_ID, values.getAsInteger("product_ID") )
        }
    }

    fun Categories.insert(values: ContentValues) {
        cupdb.insert(Categories) {
            set( it.category_ID, values.getAsInteger("category_ID") )
            set( it.category_name, values.getAsString("category_name") )
        }
    }

    fun Categories_Products.insert(values: ContentValues) {
        cupdb.insert(Categories_Products) {
            set( it.category_ID, values.getAsInteger("category_ID") )
            set( it.product_ID, values.getAsInteger("product_ID") )
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun Services.insert(values: ContentValues) {
        cupdb.insert(Services) {
            set( it.service_ID, values.getAsInteger("service_ID") )
            set( it.service_name, values.getAsString("service_name") )
            set( it.time_opening, LocalTime.parse(values.getAsString("time_opening")) )
            set( it.time_ending, LocalTime.parse(values.getAsString("time_ending")) )
            set( it.store_ID, values.getAsInteger("store_ID") )
        }
    }

    fun Services_Categories.insert(values: ContentValues) {
        cupdb.insert(Services_Categories) {
            set( it.service_ID, values.getAsInteger("service_ID") )
            set( it.category_ID, values.getAsInteger("category_ID") )
        }
    }

    fun FoodIngredients.insert(values: ContentValues) {
        cupdb.insert(FoodIngredients) {
            set( it.ingredient_ID, values.getAsInteger("ingredient_ID") )
            set( it.ingredient_name, values.getAsString("ingredient_name") )
        }
    }

    fun Products_Ingredients.insert(values: ContentValues) {
        cupdb.insert(Products_Ingredients) {
            set( it.product_ID, values.getAsInteger("product_ID") )
            set( it.ingredient_ID, values.getAsInteger("ingredient_ID") )
        }
    }

    fun Nutrients.insert(values: ContentValues) {
        cupdb.insert(Nutrients) {
            set( it.nutrient_ID, values.getAsInteger("nutrient_ID") )
            set( it.nutrient_name, values.getAsString("nutrient_name") )
        }
    }

    fun Ingredients_Nutrients.insert(values: ContentValues) {
        cupdb.insert(Ingredients_Nutrients) {
            set( it.ingredient_ID, values.getAsInteger("ingredient_ID") )
            set( it.nutrient_ID, values.getAsInteger("nutrient_ID") )
        }
    }
}