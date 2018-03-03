package com.codiply.scalawithcats.chapter3

import org.scalatest.{FlatSpec, Matchers}

class CodecSpec extends FlatSpec with Matchers {
  behavior of "Codec"

  import CodecInstances._

  it should "encode and decode a double" in {
    val codec = implicitly[Codec[Double]]

    codec.decode("2.0") should be (2.0)
    codec.encode(3.0) should be ("3.0")
  }

  it should "encode and decode a Box[Double]" in {
    val codec = implicitly[Codec[Box[Double]]]

    codec.encode(Box(123.4)) should be ("123.4")
    codec.decode("123.4") should be (Box(123.4))
  }
}
