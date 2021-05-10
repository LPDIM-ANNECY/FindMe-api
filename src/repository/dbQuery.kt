package fr.find.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jetbrains.exposed.sql.transactions.transaction

suspend fun <T> dbQuery(block: () -> T): Unit =
    withContext(Dispatchers.IO) {
        transaction { block() }
    }

