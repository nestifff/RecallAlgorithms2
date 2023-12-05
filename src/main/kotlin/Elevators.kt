import java.lang.Math.abs

class Building {

    private val elevators: MutableList<Elevator> = mutableListOf()

    fun requestElevator(floor: Int, direction: Direction) {
        val elevator = selectElevator(floor, direction)
        elevator.moveToFloor(floor)
    }

    private fun selectElevator(floor: Int, direction: Direction) : Elevator {
        var selectedElevator: Elevator? = null
        var minFloorsToPass = Int.MAX_VALUE
        for (elevator in elevators) {
            if (elevator.direction == null) {
                if (abs(elevator.currentFloor - floor) < minFloorsToPass) {
                    minFloorsToPass = abs(elevator.currentFloor - floor)
                    selectedElevator = elevator
                }
            }
        }
        if (selectedElevator != null) {
            return selectedElevator
        }
        minFloorsToPass = Int.MAX_VALUE
        for (elevator in elevators) {
            if (elevator.direction == direction) {
                val currFloorsToPass = if (direction == Direction.MOVING_UP) {
                    floor - elevator.currentFloor
                } else {
                    elevator.currentFloor - floor
                }
                if (currFloorsToPass in 0 until minFloorsToPass) {
                    minFloorsToPass = currFloorsToPass
                    selectedElevator = elevator
                }
            }
        }
        if (selectedElevator == null) {
            selectedElevator = elevators.random()
        }
        return selectedElevator
    }

}

class Elevator {

    var currentFloor: Int = 1
    var direction: Direction? = null

    val floorsToMoveUp: MutableList<Int> = mutableListOf()
    val floorsToMoveDown: MutableList<Int> = mutableListOf()

    fun moveToFloor(floor: Int) {
        if (currentFloor > floor) {
            floorsToMoveDown.add(floor)
            floorsToMoveDown.sortByDescending { it }
        } else if (currentFloor < floor) {
            floorsToMoveUp.add(floor)
            floorsToMoveUp.sort()
        }
    }

    fun move() {
        while(true) {
            // went to some floor
            if (direction == Direction.MOVING_UP && floorsToMoveUp.isNotEmpty()) {
                // next floor to visit -> floorsToMoveUp.first()
            }
            if (direction == Direction.MOVING_DOWN && floorsToMoveDown.isNotEmpty()) {
                // next floor to visit -> floorsToMoveDown.first()
            }
            if (direction == Direction.MOVING_UP && floorsToMoveUp.isEmpty()) {
                if (floorsToMoveDown.isNotEmpty()) {
                    direction = Direction.MOVING_DOWN
                    // next floor to visit -> floorsToMoveDown.first()
                } else {
                    direction = null
                }
            }
            if (direction == Direction.MOVING_DOWN && floorsToMoveDown.isEmpty()) {
                if (floorsToMoveUp.isNotEmpty()) {
                    direction = Direction.MOVING_UP
                    // next floor to visit -> floorsToMoveDown.first()
                } else {
                    direction = null
                }
            }
        }
    }
}

enum class Direction {
    MOVING_UP, MOVING_DOWN
}
