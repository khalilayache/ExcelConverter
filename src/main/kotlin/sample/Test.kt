package sample

data class Customer(
    var id: String? = null,
    var name: String? = null,
    var address: String? = null,
    var age: Int = 0
) {
    override fun toString(): String {
        return "sample.Customer [id=$id, name=$name, address=$address, age=$age]"
    }
}

//data class sample.Headers(val index: Int, val text: String)

enum class HEAD(val index: Int, val text: String){
    ID(0, "Id"),
    NAME(1,"Name"),
    ADDRESS(2, "Address"),
    AGE(3, "Age")
}
