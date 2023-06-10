interface GameState {
  cells: Cell[]
  turn: number
  winner: number // 0 if no winner
  stage: number
  lastChosen: String
  chosen: boolean
}

interface Cell {
  text: string
  playerId: number
  hasPlayer1Worker: boolean
  hasPlayer2Worker: boolean
  selected: boolean
  x: number
  y: number
}

export type { GameState, Cell }
