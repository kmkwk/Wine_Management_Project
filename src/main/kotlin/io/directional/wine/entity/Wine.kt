package io.directional.wine.entity

import io.directional.wine.payload.request.CreateWineRequest
import io.directional.wine.payload.request.UpdateWineRequest
import jakarta.persistence.*

@Entity
@Table(name = "wine", indexes =[
        Index(name = "idx_wine_name_english", columnList = "name_english"),
        Index(name = "idx_wine_name_korean", columnList = "name_korean"),
        Index(name = "idx_wine_price", columnList = "price"),
        ])
class Wine(

    @Id
    @Column(name = "wine_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    var type: String,

    @Column(name = "name_korean")
    var nameKorean: String,

    @Column(name = "name_english")
    var nameEnglish: String,

    var alcohol: Double,

    var acidity: Int,

    var body: Int,

    var sweetness: Int,

    var tannin: Int,

    var servingTemperature: Double,

    var score: Double,

    @Column(name = "price")
    var price: Int,

    var style: String?,

    var grade: String?,

    var deleted: Boolean = false,

    @OneToMany(mappedBy = "wine")
    val aroma: List<WineAroma> = emptyList(),

    @OneToMany(mappedBy = "wine")
    val pairing: List<WinePairing> = emptyList(),

    @OneToMany(mappedBy = "wine")
    val wineGrape: List<WineGrape> = emptyList(),

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "winery_id")
    var winery: Winery,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "importer_id")
    var importer: Importer,

    ) : BaseTime() {
    companion object {
        fun toEntity(createWineRequest: CreateWineRequest, winery: Winery, importer: Importer): Wine {
            // Wine 엔티티로 변환하는 로직 작성
            return Wine(
                type = createWineRequest.type,
                nameKorean = createWineRequest.nameKorean,
                nameEnglish = createWineRequest.nameEnglish,
                alcohol = createWineRequest.alcohol,
                acidity = createWineRequest.acidity,
                body = createWineRequest.body,
                sweetness = createWineRequest.sweetness,
                tannin = createWineRequest.tannin,
                servingTemperature = createWineRequest.servingTemperature,
                score = createWineRequest.score,
                price = createWineRequest.price,
                style = createWineRequest.style,
                grade = createWineRequest.grade,
                importer = importer,
                winery = winery,
            )
        }
    }

    fun setWineInfo(updateWineRequest: UpdateWineRequest, winery: Winery, importer: Importer) {
        this.type = updateWineRequest.type
        this.nameKorean = updateWineRequest.nameKorean
        this.nameEnglish = updateWineRequest.nameEnglish
        this.alcohol = updateWineRequest.alcohol
        this.acidity = updateWineRequest.acidity
        this.body = updateWineRequest.body
        this.sweetness = updateWineRequest.sweetness
        this.tannin = updateWineRequest.tannin
        this.servingTemperature = updateWineRequest.servingTemperature
        this.score = updateWineRequest.score
        this.price = updateWineRequest.price
        this.style = updateWineRequest.style
        this.grade = updateWineRequest.grade
        this.winery = winery
        this.importer = importer
    }

    fun setDeleted() {
        this.deleted = true
    }
}

