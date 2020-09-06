package model

enum class Platform(private val id: Int, private val text: String) {
    MOBILLS(1, Mobills.NAME),
    ORGANIZZE(2, Organizze.NAME);

    companion object {
        fun getPlatformById(id: Int): Platform? {
            for (platform in values()) {
                if (platform.id == id) {
                    return platform
                }
            }
            return null
        }

        fun printAllIdsAndNames(){
            for (platform in values()) {
                println(platform.toString())
            }
        }
    }

    fun getName(): String {
            return text
        }

    override fun toString(): String {
        return "$id - $text"
    }
}