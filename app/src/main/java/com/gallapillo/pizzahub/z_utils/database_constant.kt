package com.gallapillo.pizzahub.z_utils

import com.google.firebase.database.DatabaseReference

lateinit var REF_DATABASE_ROOT: DatabaseReference
const val REF_STORAGE_ROOT = ""

// FOR pizza model
const val NODE_PIZZA = "Pizza"
const val NODE_PIZZA_NAME = "Name"
const val NODE_PIZZA_PRICE = "Price"
const val NODE_PIZZA_IMAGE_URL = "Image_URL"