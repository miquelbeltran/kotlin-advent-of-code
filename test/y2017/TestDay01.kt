package y2017

import org.testng.Assert
import org.testng.annotations.Test
import y2017.solveDay1

class TestDay01 {

    @Test
    fun `Given 1122 then 3`() {
        Assert.assertEquals(solveDay1("1122"), 3)
    }

    @Test
    fun `Given 1111 then 4`() {
        Assert.assertEquals(solveDay1("1111"), 4)
    }

    @Test
    fun `Given 1234 then 0`() {
        Assert.assertEquals(solveDay1("1234"), 0)
    }

    @Test
    fun `Given 91212129 then 9`() {
        Assert.assertEquals(solveDay1("91212129"), 9)
    }

    @Test
    fun `Given 1212, when halfway, then 6`() {
        Assert.assertEquals(solveDay1("1212", true), 6)
    }

    @Test
    fun `Given 1221, when halfway, then 0`() {
        Assert.assertEquals(solveDay1("1221", true), 0)
    }

    @Test
    fun `Given 123425, when halfway, then 4`() {
        Assert.assertEquals(solveDay1("123425", true), 4)
    }

    @Test
    fun `Given 123123, when halfway, then 12`() {
        Assert.assertEquals(solveDay1("123123", true), 12)
    }

    @Test
    fun `Given 12131415, when halfway, then 4`() {
        Assert.assertEquals(solveDay1("12131415", true), 4)
    }
}