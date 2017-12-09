import org.testng.annotations.Test
import kotlin.test.assertEquals

/*
Here are some self-contained pieces of garbage:

<>, empty garbage.
<random characters>, garbage containing random characters.
<<<<>, because the extra < are ignored.
<{!>}>, because the first > is canceled.
<!!>, because the second ! is canceled, allowing the > to terminate the garbage.
<!!!>>, because the second ! and the first > are canceled.
<{o"i!a,<{i<a>, which ends at the first >.
Here are some examples of whole streams and the number of groups they contain:

{}, 1 group.
{{{}}}, 3 groups.
{{},{}}, also 3 groups.
{{{},{},{{}}}}, 6 groups.
{<{},{},{{}}>}, 1 group (which itself contains garbage).
{<a>,<a>,<a>,<a>}, 1 group.
{{<a>},{<a>},{<a>},{<a>}}, 5 groups.
{{<!>},{<!>},{<!>},{<a>}}, 2 groups (since all but the last > are canceled).
Your goal is to find the total score for all groups in your input. Each group is assigned a score which is one more than the score of the group that immediately contains it. (The outermost group gets a score of 1.)

{}, score of 1.
{{{}}}, score of 1 + 2 + 3 = 6.
{{},{}}, score of 1 + 2 + 2 = 5.
{{{},{},{{}}}}, score of 1 + 2 + 3 + 3 + 3 + 4 = 16.
{<a>,<a>,<a>,<a>}, score of 1.
{{<ab>},{<ab>},{<ab>},{<ab>}}, score of 1 + 2 + 2 + 2 + 2 = 9.
{{<!!>},{<!!>},{<!!>},{<!!>}}, score of 1 + 2 + 2 + 2 + 2 = 9.
{{<a!>},{<a!>},{<a!>},{<ab>}}, score of 1 + 2 = 3.
 */

class TestDay9 {
    @Test
    fun `{}, score of 1`() {
        val input = "{}"
        assertEquals(1, solveDay9(input).first)
    }

    @Test
    fun `{{{}}}, score of 6`() {
        val input = "{{{}}}"
        assertEquals(6, solveDay9(input).first)
    }

    @Test
    fun `{{},{}}, score of 5`() {
        val input = "{{},{}}"
        assertEquals(5, solveDay9(input).first)
    }

    @Test
    fun `{{{},{},{{}}}} score of 16`() {
        val input = "{{{},{},{{}}}}"
        assertEquals(16, solveDay9(input).first)
    }

    @Test
    fun `garbage score of 1`() {
        val input = "{<a>,<a>,<a>,<a>}"
        assertEquals(1, solveDay9(input).first)
    }

    @Test
    fun garbage1() {
        val input = "{{<ab>},{<ab>},{<ab>},{<ab>}},"
        assertEquals(9, solveDay9(input).first)
    }

    @Test
    fun garbage2() {
        val input = "{{<!!>},{<!!>},{<!!>},{<!!>}},"
        assertEquals(9, solveDay9(input).first)
    }

    @Test
    fun garbage3() {
        val input = "{{<a!>},{<a!>},{<a!>},{<ab>}},"
        assertEquals(3, solveDay9(input).first)
    }

    @Test
    fun garbageCount1() {
        val input = "<>"
        assertEquals(0, solveDay9(input).second)
    }

    @Test
    fun garbageCount2() {
        val input = "<random characters>"
        assertEquals(17, solveDay9(input).second)
    }

    @Test
    fun garbageCount3() {
        val input = "<<<<>"
        assertEquals(3, solveDay9(input).second)
    }

    @Test
    fun garbageCount4() {
        val input = "<{!>}>"
        assertEquals(2, solveDay9(input).second)
    }

    @Test
    fun garbageCount5() {
        val input = "<!!>"
        assertEquals(0, solveDay9(input).second)
    }

    @Test
    fun garbageCount6() {
        val input = "<!!!>>"
        assertEquals(0, solveDay9(input).second)
    }

    @Test
    fun garbageCount7() {
        val input = "<{o\"i!a,<{i<a>"
        assertEquals(10, solveDay9(input).second)
    }
}

