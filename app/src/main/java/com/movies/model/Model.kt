package com.movies.model

object Model {

    data class Response(var data: ListResponse)
    data class ListResponse(var movies: List<Model.Movie>?)

    /*
"title": "The Shawshank Redemption",
				"year": "1994",
				"urlPoster": "https://ia.media-imdb.com/images/M/MV5BMDFkYTc0MGEtZmNhMC00ZDIzLWFmNTEtODM1ZmRlYWMwMWFmXkEyXkFqcGdeQXVyMTMxODk2OTU@._V1_UY67_CR0,0,45,67_AL_.jpg",
				"idIMDB": "tt0111161",
				"rating": "9.2",
				"ranking": 1
 */
    data class Movie(var title: String?,
                     var year: String?,
                     var urlPoster: String?,
                     var idIMDB: String?,
                     var rating: String?,
                     var ranking: Int?)
}