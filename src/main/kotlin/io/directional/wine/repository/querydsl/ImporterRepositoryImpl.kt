package io.directional.wine.repository.querydsl

import com.querydsl.jpa.impl.JPAQueryFactory
import io.directional.wine.entity.QImporter
import io.directional.wine.entity.QWine
import io.directional.wine.payload.response.ImporterWithWineResponse
import io.directional.wine.payload.response.QImporterWithWineResponse

class ImporterRepositoryImpl(
    private val jpaQueryFactory: JPAQueryFactory,
    private val qImporter: QImporter = QImporter.importer,
    private val qWine: QWine = QWine.wine
) : ImporterRepositoryCustom {

    override fun findImporterNameWithWine(importerName: String): ImporterWithWineResponse? {

        return jpaQueryFactory
            .select(
                QImporterWithWineResponse
                    (
                    qImporter.name,
                    qWine.nameEnglish,
                    qWine.nameKorean
                )
            ).from(qImporter)
            .join(qImporter.wines, qWine)
            .where(
                qImporter.deleted.isFalse.and(qWine.deleted.isFalse)
                    .and(qImporter.name.eq(importerName))
            )
            .orderBy(qImporter.name.asc())
            .fetchFirst()
    }
}