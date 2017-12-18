package y2016

import org.testng.annotations.Test
import kotlin.test.assertEquals


class Day03Test {
   @Test
   fun part1() {
       assertEquals(0, Day03.part1("5 10 25").toInt())
       assertEquals(0, Day03.part1("5 10 15").toInt())
       assertEquals(0, Day03.part1("7 7 14").toInt())
       assertEquals(1, Day03.part1("7 7 13").toInt())
   }
}