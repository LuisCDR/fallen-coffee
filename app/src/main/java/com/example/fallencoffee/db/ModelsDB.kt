package com.example.fallencoffee.db

import org.ktorm.schema.*
import org.ktorm.entity.Entity
import org.ktorm.database.Database
import org.ktorm.entity.sequenceOf
import java.time.LocalDate
import java.time.LocalTime
import java.time.LocalDateTime

interface Store : Entity<Store> {
    companion object : Entity.Factory<Store>()

    val store_ID: Int
    val store_name: String
    val store_location: String?
    val references: String?
    val fundation: LocalDate?
    val s_image: ByteArray?
    val l_image: ByteArray?
    var QRcode_image: ByteArray?
}

object Stores : Table<Store>("Stores", "sto") {
    val store_ID = int("store_ID").primaryKey().bindTo { it.store_ID }
    val store_name = varchar("store_name").bindTo { it.store_name }
    val store_location = varchar("store_location").bindTo { it.store_location }
    val references = text("references").bindTo { it.references }
    val fundation = date("fundation").bindTo { it.fundation }
    val s_image = blob("s_image").bindTo { it.s_image }
    val l_image = blob("l_image").bindTo { it.l_image }
    val QRcode_image = blob("QRcode_image").bindTo { it.QRcode_image }
}

interface Table_tmp : Entity<Table_tmp> {
    companion object : Entity.Factory<Table_tmp>()

    val table_ID: Int
    var store_ID: Store
    val quantity_users: Int
}

object Tables_tmp : Table<Table_tmp>("Tables", "tab") {
    val table_ID = int("table_ID").primaryKey().bindTo { it.table_ID }
    val store_ID = int("stores_ID").references(Stores) { it.store_ID }
    val quantity_users = int("quantity_users").bindTo { it.quantity_users }
}

interface User : Entity<User> {
    companion object : Entity.Factory<User>()

    val user_ID: Int
    var username: String
    var email: String
    var password: String
    var date_account: LocalDate
    var table_ID: Table_tmp?
    var s_image: ByteArray?
    var m_image: ByteArray?
    var QRcode_image: ByteArray?
}

object Users : Table<User>("Users", "use") {
    val user_ID = int("user_ID").primaryKey().bindTo { it.user_ID }
    val username = varchar("username").bindTo { it.username }
    val email = varchar("email").bindTo { it.email }
    val password = varchar("password").bindTo { it.password }
    val date_account = date("date_account").bindTo { it.date_account }
    val table_ID = int("table_ID").references(Tables_tmp) { it.table_ID }
    val s_image = blob("s_image").bindTo { it.s_image }
    val m_image = blob("m_image").bindTo { it.m_image }
    val QRcode_image = blob("QRcode_image").bindTo { it.QRcode_image }
}

interface Order : Entity<Order> {
    companion object : Entity.Factory<Order>()

    val order_ID: Int
    var order_date: LocalDateTime
    var user_ID: User
}

object Orders : Table<Order>("Orders", "ord") {
    val order_ID = int("order_ID").primaryKey().bindTo { it.order_ID }
    val order_date = datetime("order_date").bindTo { it.order_date }
    val user_ID = int("user_ID").references(Users) { it.user_ID }
}

interface Contact : Entity<Contact> {
    companion object : Entity.Factory<Contact>()

    val contact_ID: Int
    var user_ID: User
    var contact_category: ContactCategory
    var is_selected: Boolean
    val date_added: LocalDate
}

object Contacts : Table<Contact>("Contacts", "con") {
    val contact_ID = int("contact_ID").primaryKey().bindTo { it.contact_ID }
    val user_ID = int("user_ID").references(Users) { it.user_ID }
    val contact_category = varchar("contact_category").references(ContactCategories) { it.contact_category }
    val is_selected = boolean("is_selected").bindTo { it.is_selected }
    val date_added = date("date_added").bindTo { it.date_added }
}

interface User_Contact : Entity<User_Contact> {
    companion object : Entity.Factory<User_Contact>()

    var user_ID: User
    var contact_ID: Contact
}

object Users_Contacts : Table<User_Contact>("Users_Contacts", "use_con") {
    val user_ID = int("user_ID").references(Users) { it.user_ID }
    val contact_ID = int("contact_ID").references(Contacts) { it.contact_ID }
}

interface ContactCategory : Entity<ContactCategory> {
    companion object : Entity.Factory<ContactCategory>()

    val contact_category: String
    val discount: Double
}

object ContactCategories : Table<ContactCategory>("ContactCategories", "conCat") {
    val contact_category = varchar("contact_category").primaryKey().bindTo { it.contact_category }
    val discount = double("discount").bindTo { it.discount }
}

interface SearchTag : Entity<SearchTag> {
    companion object : Entity.Factory<SearchTag>()

    val tag_ID: Int
    var tag_name: String
}

object SearchTags : Table<SearchTag>("SearchTags", "tag") {
    val tag_ID = int("tag_ID").primaryKey().bindTo { it.tag_ID }
    val tag_name = varchar("tag_name").bindTo { it.tag_name }
}

interface Contact_Tag : Entity<Contact_Tag> {
    companion object : Entity.Factory<Contact_Tag>()

    var contact_ID: Contact
    var tag_ID: SearchTag
}

object Contacts_Tags : Table<Contact_Tag>("Contacts_Tags", "con_tag") {
    val contact_ID = int("contact_ID").references(Contacts) { it.contact_ID }
    val tag_ID = int("tag_ID").references(SearchTags) { it.tag_ID }
}

interface ComboPack : Entity<ComboPack> {
    companion object : Entity.Factory<ComboPack>()

    val combo_ID: Int
    var combo_name: String
    var temperature: String
}

object ComboPacks : Table<ComboPack>("ComboPack", "comPac") {
    val combo_ID = int("combo_ID").primaryKey().bindTo { it.combo_ID }
    val combo_name = varchar("combo_name").bindTo { it.combo_name }
    val temperature = varchar("temperature").bindTo { it.temperature }
}

interface User_Combo : Entity<User_Combo> {
    companion object : Entity.Factory<User_Combo>()

    var user_ID: User
    var combo_ID: ComboPack
}

object Users_Combos : Table<User_Combo>("Users_Combos", "use_com") {
    val user_ID = int("user_ID").references(Users) { it.user_ID }
    val combo_ID = int("combo_ID").references(ComboPacks) { it.combo_ID }
}

interface Craving : Entity<Craving> {
    companion object : Entity.Factory<Craving>()

    val craving_ID: Int
    var craving_type: String
    var craving_name: String
    var craving_quantity: String
}

object Cravings : Table<Craving>("Craving", "cra") {
    val craving_ID = int("craving_ID").primaryKey().bindTo { it.craving_ID }
    val craving_type = varchar("craving_type").bindTo { it.craving_type }
    val craving_name = varchar("craving_name").bindTo { it.craving_name }
    val craving_quantity = varchar("craving_quantity").bindTo { it.craving_quantity }
}

interface Combo_Craving : Entity<Combo_Craving> {
    companion object : Entity.Factory<Combo_Craving>()

    var combo_ID: ComboPack
    var craving_ID: Craving
}

object Combos_Cravings : Table<Combo_Craving>("Combo_Craving", "com_cra") {
    val combo_ID = int("combo_ID").references(ComboPacks) { it.combo_ID }
    val craving_ID = int("craving_ID").references(Cravings) { it.craving_ID }
}

interface HealthyAlternative : Entity<HealthyAlternative> {
    companion object : Entity.Factory<HealthyAlternative>()

    val ha_ID: Int
    var food_alternative: String
}

object HealthyAlternatives : Table<HealthyAlternative>("HealthyAltenatives", "ha") {
    val ha_ID = int("ha_ID").primaryKey().bindTo { it.ha_ID }
    val food_alternative = varchar("food_alternative").bindTo { it.food_alternative }
}

interface Combo_HA : Entity<Combo_HA> {
    companion object : Entity.Factory<Combo_HA>()

    var combo_ID: ComboPack
    var ha_ID: HealthyAlternative
}

object Combos_HA : Table<Combo_HA>("Combo_HA", "com_ha") {
    val combo_ID = int("combo_ID").references(ComboPacks) { it.combo_ID }
    val ha_ID = int("ha_ID").references(HealthyAlternatives) { it.ha_ID }
}

interface Product : Entity<Product> {
    companion object : Entity.Factory<Product>()

    val product_ID: Int
    var product_name: String
    var taste: String
    var price: Double
    val s_image: ByteArray?
    val l_image: ByteArray?
    var prep_time: LocalTime?
}

object Products : Table<Product>("Products", "pro") {
    val product_ID = int("product_ID").primaryKey().bindTo { it.product_ID }
    val product_name = varchar("product_name").bindTo { it.product_name }
    val taste = varchar("taste").bindTo { it.taste }
    val price = double("price").bindTo { it.price }
    val s_image = blob("s_image").bindTo { it.s_image }
    val l_image = blob("l_image").bindTo { it.l_image }
    val prep_time = time("prep_time").bindTo { it.prep_time }
}

interface Combo_Product : Entity<Combo_Product> {
    companion object : Entity.Factory<Combo_Product>()

    var combo_ID: ComboPack
    var product_ID: Product
}

object Combos_Products : Table<Combo_Product>("Combo_Product", "com_pro") {
    val combo_ID = int("combo_ID").references(ComboPacks) { it.combo_ID }
    val product_ID = int("product_ID").references(Products) { it.product_ID }
}

interface Category : Entity<Category> {
    companion object : Entity.Factory<Category>()

    val category_ID: Int
    var category_name: String
}

object Categories : Table<Category>("Categories", "cat") {
    val category_ID = int("category_ID").primaryKey().bindTo { it.category_ID }
    val category_name = varchar("category_name").bindTo { it.category_name }
}

interface Category_Product : Entity<Category_Product> {
    companion object : Entity.Factory<Category_Product>()

    var category_ID: Category
    var product_ID: Product
}

object Categories_Products : Table<Category_Product>("Categories_Products", "cat_pro") {
    val category_ID = int("category_ID").references(Categories) { it.category_ID }
    val product_ID = int("product_ID").references(Products) { it.product_ID }
}

interface Service : Entity<Service> {
    companion object : Entity.Factory<Service>()

    val service_ID: Int
    var service_name: String
    val time_opening: LocalTime
    val time_ending: LocalTime
    var store_ID: Store
}

object Services : Table<Service>("Services", "ser") {
    val service_ID = int("services_ID").primaryKey().bindTo { it.service_ID }
    val service_name = varchar("service_name").bindTo { it.service_name }
    val time_opening = time("time_opening").bindTo { it.time_opening }
    val time_ending = time("time_endind").bindTo { it.time_ending }
    val store_ID = int("store_ID").references(Stores) { it.store_ID }
}

interface Service_Category : Entity<Service_Category> {
    companion object : Entity.Factory<Service_Category>()

    var service_ID: Service
    var category_ID: Category
}

object Services_Categories : Table<Service_Category>("Services_Categories", "ser_cat") {
    val service_ID = int("service_ID").references(Services) { it.service_ID }
    val category_ID = int("category_ID").references(Categories) { it.category_ID }
}

interface FoodIngredient : Entity<FoodIngredient> {
    companion object : Entity.Factory<FoodIngredient>()

    val ingredient_ID: Int
    var ingredient_name: String
}

object FoodIngredients : Table<FoodIngredient>("FoodIngredients", "ing") {
    val ingredient_ID = int("ingredient_ID").primaryKey().bindTo { it.ingredient_ID }
    val ingredient_name = varchar("ingredient_name").bindTo { it.ingredient_name }
}

interface Product_Ingredient : Entity<Product_Ingredient> {
    companion object : Entity.Factory<Product_Ingredient>()

    var product_ID: Product
    var ingredient_ID: FoodIngredient
}

object Products_Ingredients : Table<Product_Ingredient>("Products_Ingredients", "pro_ing") {
    val product_ID = int("product_ID").references(Products) { it.product_ID }
    val ingredient_ID = int("ingredient_ID").references(FoodIngredients) { it.ingredient_ID }
}

interface Nutrient : Entity<Nutrient> {
    companion object : Entity.Factory<Nutrient>()

    val nutrient_ID: Int
    var nutrient_name: String
}

object Nutrients : Table<Nutrient>("Nutrients", "nut") {
    val nutrient_ID = int("nutrient_ID").primaryKey().bindTo { it.nutrient_ID }
    val nutrient_name = varchar("nutrient_name").bindTo { it.nutrient_name }
}

interface Ingredient_Nutrient : Entity<Ingredient_Nutrient> {
    companion object : Entity.Factory<Ingredient_Nutrient>()

    var ingredient_ID: FoodIngredient
    var nutrient_ID: Nutrient
    var nutrient_proportion: Double
}

object Ingredients_Nutrients : Table<Ingredient_Nutrient>("Ingredients_Nutrients", "ing_nut") {
    val ingredient_ID = int("ingredient_ID").references(FoodIngredients) { it.ingredient_ID }
    val nutrient_ID = int("nutrient_ID").references(Nutrients) { it.nutrient_ID }
    val nutrient_proportion = double("nutrient_proportion").bindTo { it.nutrient_proportion }
}

val Database.stores get() = this.sequenceOf(Stores)
val Database.table_tmp get() = this.sequenceOf(Tables_tmp, withReferences = true)
val Database.users get() = this.sequenceOf(Users, withReferences = true)
val Database.orders get() = this.sequenceOf(Orders, withReferences = true)
val Database.contacts get() = this.sequenceOf(Contacts, withReferences = true)
val Database.users_contacts get() = this.sequenceOf(Users_Contacts, withReferences = true)
val Database.contactCategories get() = this.sequenceOf(ContactCategories)
val Database.searchTags get() = this.sequenceOf(SearchTags)
val Database.contacts_tags get() = this.sequenceOf(Contacts_Tags, withReferences = true)
val Database.comboPacks get() = this.sequenceOf(ComboPacks)
val Database.users_combos get() = this.sequenceOf(Users_Combos, withReferences = true)
val Database.cravings get() = this.sequenceOf(Cravings)
val Database.combos_cravings get() = this.sequenceOf(Combos_Cravings, withReferences = true)
val Database.healthyAlternatives get() = this.sequenceOf(HealthyAlternatives)
val Database.combos_ha get() = this.sequenceOf(Combos_HA, withReferences = true)
val Database.products get() = this.sequenceOf(Products)
val Database.combos_products get() = this.sequenceOf(Combos_Products, withReferences = true)
val Database.categories get() = this.sequenceOf(Categories)
val Database.categories_products get() = this.sequenceOf(Categories_Products, withReferences = true)
val Database.services get() = this.sequenceOf(Services, withReferences = true)
val Database.services_categories get() = this.sequenceOf(Services_Categories, withReferences = true)
val Database.foodIngredients get() = this.sequenceOf(FoodIngredients)
val Database.products_ingredients get() = this.sequenceOf(Products_Ingredients, withReferences = true)
val Database.nutrients get() = this.sequenceOf(Nutrients)
val Database.ingredients_nutrients get() = this.sequenceOf(Ingredients_Nutrients, withReferences = true)

class T {
    val sto = Stores
    val tab = Tables_tmp
    val use = Users
    val ord = Orders
    val con = Contacts
    val use_con = Users_Contacts
    val conCat = ContactCategories
    val tag = SearchTags
    val con_tag = Contacts_Tags
    val comPac = ComboPacks
    val use_com = Users_Combos
    val cra = Cravings
    val com_cra = Combos_Cravings
    val hA = HealthyAlternatives
    val com_hA = Combos_HA
    val pro = Products
    val com_pro = Combos_Products
    val cat = Categories
    val cat_pro = Categories_Products
    val ser = Services
    val ser_cat = Services_Categories
    val ing = FoodIngredients
    val pro_ing = Products_Ingredients
    val nut = Nutrients
    val ing_nut = Ingredients_Nutrients
}