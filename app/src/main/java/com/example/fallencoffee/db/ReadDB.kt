package com.example.fallencoffee.db

import org.ktorm.dsl.*

class ReadDB {
    private val cupdb = CupDB().connection()
    private val t = T()

    fun listProduct_Category(category_name : String) : Query {
        return cupdb
            .from(t.sto)
            .innerJoin(t.ser, t.ser.store_ID eq t.sto.store_ID)
            .innerJoin(t.ser_cat, t.ser_cat.service_ID eq t.ser.service_ID)
            .innerJoin(t.cat, t.cat.category_ID eq t.ser_cat.category_ID)
            .innerJoin(t.cat_pro, t.cat_pro.category_ID eq t.cat.category_ID)
            .innerJoin(t.pro, t.pro.product_ID eq t.cat_pro.product_ID)
            .select(
                t.sto.store_name, t.ser.service_name,
                t.pro.product_ID, t.pro.product_name,
                t.pro.s_image)
            .where { (t.cat.category_name like "%$category_name%") }
    }

    fun existUserBy(username : String): Boolean {
        val subQuery = cupdb
            .from(t.use)
            .select(t.use.username)
            .where{ (t.use.username like "%$username") }
        return exists(subQuery).notExists
    }
}