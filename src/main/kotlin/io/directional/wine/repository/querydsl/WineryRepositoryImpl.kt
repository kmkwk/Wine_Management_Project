package io.directional.wine.repository.querydsl

import com.querydsl.jpa.impl.JPAQueryFactory
import io.directional.wine.entity.QRegion
import io.directional.wine.entity.QWine
import io.directional.wine.entity.QWinery
import io.directional.wine.payload.response.QWineryWithRegionResponse
import io.directional.wine.payload.response.QWineryWithRegionWithWineNameResponse
import io.directional.wine.payload.response.WineryWithRegionResponse
import io.directional.wine.payload.response.WineryWithRegionWithWineNameResponse

class WineryRepositoryImpl(
    private val jpaQueryFactory: JPAQueryFactory,
    private val qWinery: QWinery = QWinery.winery,
    private val qRegion: QRegion = QRegion.region,
    private val qWine: QWine = QWine.wine
) : WineryRepositoryCustom {

    override fun findWineryWithRegion(wineryName: String, wineryRegion: String): WineryWithRegionWithWineNameResponse? {

        return jpaQueryFactory
            .select(
                QWineryWithRegionWithWineNameResponse(
                    qWinery.nameEnglish,
                    qWinery.nameKorean,
                    qRegion.nameEnglish,
                    qRegion.nameKorean,
                    qWine.nameEnglish,
                    qWine.nameKorean
                )
            ).from(qWinery)
            .join(qWinery.region, qRegion)
            .join(qWinery.wine, qWine)
            .where(
                qWinery.deleted.isFalse.and(
                    qRegion.deleted.isFalse.and(
                        qWine.deleted.isFalse
                            .and(
                                qWinery.nameEnglish.eq(wineryName).or(qWinery.nameKorean.eq(wineryName))
                                    .and(
                                        qWinery.region.nameEnglish.eq(wineryRegion)
                                            .or(qWinery.region.nameKorean.eq(wineryRegion))
                                    )
                            )
                    )
                )
            )
            .orderBy(qWinery.nameEnglish.asc())
            .fetchFirst()
    }

    override fun findWineryWithRegionAll(wineryName: String, wineryRegion: String): List<WineryWithRegionResponse> {

        return jpaQueryFactory
            .select(
                QWineryWithRegionResponse(
                    qWinery.nameEnglish,
                    qWinery.nameKorean,
                    qRegion.nameEnglish,
                    qRegion.nameKorean,
                )
            ).from(qWinery)
            .join(qWinery.region, qRegion)
            .where(
                qWinery.deleted.isFalse.and(
                    qRegion.deleted.isFalse
                        .and(
                            qWinery.nameEnglish.contains(wineryName)
                                .or(qWinery.nameKorean.contains(wineryName))
                                .and(
                                    qWinery.region.nameEnglish.eq(wineryRegion)
                                        .or(qWinery.region.nameKorean.eq(wineryRegion))
                                )
                        )
                )
            )
            .orderBy(qWinery.nameEnglish.asc())
            .fetch()
    }
}