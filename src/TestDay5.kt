import org.testng.annotations.Test
import javax.swing.text.StyledEditorKit
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class TestDay5 {
    @Test
    fun `jump offsets`() {
        val input = """0
3
0
1
-3
"""
        // Initial state
        val solver = MazeSolver(input.lines())
        assertEquals(0, solver.pos)
        assertEquals(0, solver.steps)
        assertFalse(solver.isExit())

        // One step
        solver.runStep()
        assertEquals(0, solver.pos)
        assertEquals(1, solver.steps)
        assertEquals(1, solver.lines[0])
        assertFalse(solver.isExit())

        // step 2
        solver.runStep()
        assertEquals(1, solver.pos)
        assertEquals(2, solver.steps)
        assertEquals(2, solver.lines[0])
        assertFalse(solver.isExit())

        // step 3
        solver.runStep()
        assertEquals(4, solver.pos)
        assertEquals(3, solver.steps)
        assertEquals(4, solver.lines[1])
        assertFalse(solver.isExit())

        // step 4
        solver.runStep()
        assertEquals(1, solver.pos)
        assertEquals(4, solver.steps)
        assertEquals(-2, solver.lines[4])
        assertFalse(solver.isExit())

        // step 5
        solver.runStep()
        assertEquals(5, solver.pos)
        assertEquals(5, solver.steps)
        assertEquals(5, solver.lines[1])
        assertTrue(solver.isExit())
    }
}

