import React from 'react'
import { Cell } from './game'

interface Props {
  cell: Cell
}

class BoardCell extends React.Component<Props> {
  render (): React.ReactNode {
    const hasPlayer1Worker = this.props.cell.hasPlayer1Worker ? 'hasPlayer1Worker' : ''
    const hasPlayer2Worker = this.props.cell.hasPlayer2Worker ? 'hasPlayer2Worker' : ''
    const selected = this.props.cell.selected ? 'selected' : ''
    return (
      <div className={`cell ${hasPlayer1Worker} ${hasPlayer2Worker} ${selected}`}>{this.props.cell.text}</div>
    )
  }
}

export default BoardCell
