package com.mustafin.main_menu_feature.mappers

import com.mustafin.core.data.source.local.budgetSources.goalsSource.BudgetGoalsEntity
import com.mustafin.core.data.source.local.budgetSources.operationSource.BudgetOperationsEntity
import com.mustafin.core.utils.budget.BudgetGoalModel
import com.mustafin.core.utils.budget.BudgetGoalStatus
import com.mustafin.core.utils.budget.BudgetOperationModel
import com.mustafin.core.utils.budget.BudgetOperationType
import com.mustafin.main_menu_feature.data.mappers.mapToBudgetGoalModel
import com.mustafin.main_menu_feature.data.mappers.mapToBudgetGoalsEntity
import com.mustafin.main_menu_feature.data.mappers.mapToBudgetOperationModel
import com.mustafin.main_menu_feature.data.mappers.mapToBudgetOperationsEntity
import junit.framework.TestCase.assertEquals
import org.junit.Test
import java.math.BigDecimal

class BudgetMappersTest {
    // Простой тест маппера без логики
    @Test
    fun `BudgetOperationsEntity mapToBudgetOperationModel test`() {
        val entityModel = BudgetOperationsEntity(
            0,
            BudgetOperationType.TOP_UP,
            BigDecimal.ZERO,
            0,
            "abc"
        )

        assertEquals(
            entityModel.mapToBudgetOperationModel(),
            BudgetOperationModel(
                0,
                BigDecimal.ZERO,
                BudgetOperationType.TOP_UP,
                0,
                "abc"
            )
        )
    }

    // Простой тест маппера без логики
    @Test
    fun `BudgetOperationModel mapToBudgetOperationsEntity test`() {
        val domainModel = BudgetOperationModel(
            0,
            BigDecimal.ZERO,
            BudgetOperationType.TOP_UP,
            0,
            "abc"
        )

        assertEquals(
            domainModel.mapToBudgetOperationsEntity(),
            BudgetOperationsEntity(
                0,
                BudgetOperationType.TOP_UP,
                BigDecimal.ZERO,
                0,
                "abc"
            )
        )
    }

    // Тест маппера при условии что закрыто 0% от цели
    @Test
    fun `BudgetGoalsEntity mapToBudgetOperationModel test with zero percent completed`() {
        val entityModel = BudgetGoalsEntity(
            0,
            "abc",
            BigDecimal.ZERO,
            BigDecimal.ONE,
            "01.03.2025",
            BudgetGoalStatus.ACTIVE
        )

        assertEquals(
            entityModel.mapToBudgetGoalModel(),
            BudgetGoalModel(
                0,
                "abc",
                BigDecimal.ONE,
                BigDecimal.ZERO,
                0f,
                "01.03.2025",
                BudgetGoalStatus.ACTIVE
            )
        )
    }

    // Тест маппера при условии что закрыто более 0% от цели
    @Test
    fun `BudgetGoalsEntity mapToBudgetOperationModel test with non-zero percent completed`() {
        val entityModel = BudgetGoalsEntity(
            0,
            "abc",
            BigDecimal(20),
            BigDecimal(100),
            "01.03.2025",
            BudgetGoalStatus.ACTIVE
        )

        assertEquals(
            entityModel.mapToBudgetGoalModel(),
            BudgetGoalModel(
                0,
                "abc",
                BigDecimal(100),
                BigDecimal(20),
                0.2f,
                "01.03.2025",
                BudgetGoalStatus.ACTIVE
            )
        )
    }

    // Простой тест маппера без логики
    @Test
    fun `BudgetGoalModel mapToBudgetGoalsEntity test`() {
        val domainModel = BudgetGoalModel(
            0,
            "abc",
            BigDecimal.ONE,
            BigDecimal.ZERO,
            0f,
            "01.03.2025",
            BudgetGoalStatus.ACTIVE
        )

        assertEquals(
            domainModel.mapToBudgetGoalsEntity(),
            BudgetGoalsEntity(
                0,
                "abc",
                BigDecimal.ZERO,
                BigDecimal.ONE,
                "01.03.2025",
                BudgetGoalStatus.ACTIVE
            )
        )
    }
}