package com.softpian.kotlinphotoapp.data.photodata


data class PhotoInfo(
    val photo: PhotoItem,
    val stat: String,
    val code: Int,
    val message: String
)

data class PhotoItem(
    val id: String,
    val secret: String,
    val server: String,
    val farm: Int,
    val dateuploaded: String,
    val isfavorite: Int,
    val license: Int,
    val safety_level: Int,
    val rotation: Int,
    val owner: Owner,
    val title: Title,
    val description: Description,
    val visibility: Visibility,
    val dates: Dates,
    val views: String,
    val editability: Editability,
    val publiceditability: Publiceditability,
    val usage: Usage,
    val comments: Comments,
    val notes: Notes,
    val people: People,
    val tags: Tags,
    val urls: Urls,
    val media: String
) {
    fun getPhotoUrl()
            = "https://farm${farm}.staticflickr.com/${server}/${id}_${secret}.jpg"
}

data class Owner(
    val nsid: String,
    val username: String,
    val realname: String,
    val location: String,
    val iconserver: String,
    val iconfarm: Int,
    val path_alias: String
) {
    fun getBuddyIcon() = "http://farm${iconfarm}.staticflickr.com/${iconserver}/buddyicons/${nsid}.jpg"

}

data class Visibility(
    val ispublic: Int,
    val isfriend: Int,
    val isfamily: Int
)

data class Title(
    val _content: String
)

data class Comments(
    val _content: Int
)

data class Urls(
    val url: List<Url>
)

data class Url(
    val type: String,
    val _content: String
)

data class Notes(
    val note: List<Any>
)

data class Dates(
    val posted: String,
    val taken: String,
    val takengranularity: Int,
    val takenunknown: Int,
    val lastupdate: String
)

data class Description(
    val _content: String
)

data class Editability(
    val cancomment: Int,
    val canaddmeta: Int
)

data class People(
    val haspeople: Int
)

data class Usage(
    val candownload: Int,
    val canblog: Int,
    val canprint: Int,
    val canshare: Int
)

data class Publiceditability(
    val cancomment: Int,
    val canaddmeta: Int
)

data class Tags(
    val tag: List<Tag>
)

data class Tag(
    val id: String,
    val author: String,
    val authorname: String,
    val raw: String,
    val _content: String,
    val machine_tag: Int
)