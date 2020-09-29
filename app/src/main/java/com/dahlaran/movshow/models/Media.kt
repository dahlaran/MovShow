package com.dahlaran.movshow.models

data class Media(
    val genres: List<String>,
    val id: Int,
    val image: Image?,
    val language: String?,
    val name: String,
    val officialSite: String?,
    val premiered: String?,
    val rating: Rating,
    val runtime: Int,
    val seasons: SeasonList?,
    val schedule: Schedule?,
    val status: String,
    val summary: String?,
    val type: String,
    val updated: Int,
    val url: String
) {
    companion object {
        fun fromTVMazeMedia(tvMaze: TVMazeMedia): Media {
            return fromTVMazeShow(tvMaze.show)
        }

        fun fromTVMazeShow(show: Show): Media {
             val seasonList = SeasonList.fromEpisodeList(show.embedded?.episodes)
            return Media(
                show.genres,
                show.id,
                show.image,
                show.language,
                show.name,
                show.officialSite,
                show.premiered,
                show.rating,
                show.runtime,
                seasonList,
                show.schedule,
                show.status,
                show.summary,
                show.type,
                show.updated,
                show.url
            )
        }
    }
}