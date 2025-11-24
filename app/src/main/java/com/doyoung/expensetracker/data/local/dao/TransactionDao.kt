package com.doyoung.expensetracker.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.doyoung.expensetracker.data.local.entity.TransactionEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TransactionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(transaction: TransactionEntity)

    @Query("DELETE FROM transactions WHERE id = :id")
    suspend fun deleteById(id: Long)

    @Query(
        """
        SELECT * FROM transactions
        ORDER BY date DESC, id DESC
        """
    )
    fun getAll(): Flow<List<TransactionEntity>>

    @Query(
        """
        SELECT SUM(
            CASE WHEN type = 'INCOME' THEN amount
                 WHEN type = 'EXPENSE' THEN -amount
                 ELSE 0 END
        ) FROM transactions
        """
    )
    fun getBalanceFlow(): Flow<Long?>
}
