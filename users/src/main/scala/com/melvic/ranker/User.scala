package com.melvic.ranker

final case class User(id: String, name: Name, age: Int, address: Address)

final case class Name(first: String, last: String)

final case class Address(value: String)
