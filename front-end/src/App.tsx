import React from 'react'
import './App.css' // import the css file to enable your styles.
import { GameState, Cell } from './game'
import BoardCell from './Cell'
// https://www.npmjs.com/package/react-confetti
import Confetti from 'react-confetti'

/**
 * Define the type of the props field for a React component
 */
// eslint-disable-next-line @typescript-eslint/no-empty-interface
interface Props { }

class App extends React.Component<Props, GameState> {
  private initialized: boolean = false
  private readonly countInit: number = 0

  /**
   * @param props has type Props
   */
  constructor (props: Props) {
    super(props)
    /**
     * state has type GameState as specified in the class inheritance.
     */
    this.state = { cells: [], turn: 0, winner: 0, stage: 0, lastChosen: '', chosen: false }
  }

  newGame: () => Promise<void> = async () => {
    const response = await fetch('/newgame')
    const json = await response.json()
    this.setState(json)
  }

  endBuild: () => Promise<void> = async () => {
    const response = await fetch('/endBuild')
    const json = await response.json()
    this.setState(json)
  }

  /**
   * play will generate an anonymous function that the component
   * can bind with.
   * @param x
   * @param y
   * @returns
   */
  play (x: number, y: number): React.MouseEventHandler {
    /* eslint-disable  @typescript-eslint/no-misused-promises */
    return async (e) => {
      // prevent the default behavior on clicking a link; otherwise, it will jump to a new page.
      e.preventDefault()
      const response = await fetch(`/play?x=${x}&y=${y}`)
      const json = await response.json()
      this.setState(json)
    }
    /* eslint-enable  @typescript-eslint/no-misused-promises */
  }

  choose: (workerType: string) => (e: React.MouseEvent) => Promise<void> = (workerType: string) => {
    return async (e) => {
      const response = await fetch(`/choose?workerType=${workerType}`)
      const json = await response.json()
      this.setState(json)
    }
  }

  createCell (cell: Cell, index: number): React.ReactNode {
    /**
     * key is used for React when given a list of items.
     */
    return (
      <div key={index}>
        <a href='/' onClick={this.play(cell.x, cell.y)}>
          <BoardCell cell={cell} />
        </a>
      </div>
    )
  }

  createInstruction (): React.ReactNode {
    if (this.state.winner !== 0) {
      return `Player ${this.state.winner} wins!`
    }
    if (this.state.stage === 0) {
      return `Player ${this.state.turn}'s turn to choose 2 workers.`
    }
    if (this.state.stage === 1) {
      return `Player ${this.state.turn}'s turn to select a worker.`
    }
    if (this.state.stage === 2) {
      return `Player ${this.state.turn}'s turn to move.`
    } else {
      return `Player ${this.state.turn}'s turn to build.`
    }
  }

  async handleChange (e: any): Promise<void> {
    const choosePower = this.choose(e.target.value)
    await choosePower(e)
  }

  /* eslint-disable  @typescript-eslint/no-misused-promises */
  createDropDown (workerType: string): React.ReactNode {
    const options = ['Normal', 'Demeter', 'Minotaur', 'Pan']
    return (
      <select onChange={async (e) => await this.handleChange(e)} style={{ width: '300px', height: '40px' }}>
        {options.map((option) => {
          if ((option !== this.state.lastChosen || option === 'Normal') && (!this.state.chosen)) {
            return <option key={option} value={option}>{option}</option>
          }
          return null
        })}
      </select>
    )
  }
  /* eslint-enable  @typescript-eslint/no-misused-promises */

  /**
   * This function will call after the HTML is rendered.
   * We update the initial state by creating a new game.
   * @see https://reactjs.org/docs/react-component.html#componentdidmount
   */
  componentDidMount (): void {
    /**
     * Use initialized to avoid setState in DidMount() rendering twice.
     */
    if (!this.initialized) {
      this.newGame().catch(err => console.log(err))
      this.initialized = true
    }
  }

  /**
   * @returns the React element via JSX.
   * @see https://reactjs.org/docs/react-component.html
   */
  render (): React.ReactNode {
    return (
      <div>
        {this.state.winner !== 0 && <Confetti />}
        <div id='instructions' className='instructions'>{this.createInstruction()}</div>
        <div id='board'>
          {this.state.cells.map((cell, i) => this.createCell(cell, i))}
        </div>
        {this.state.stage === 0 && (
          <div id='dropdown'>
            {this.createDropDown('Normal Worker')}
          </div>
        )}
        <div id='bottombar'>
          {/* eslint-disable  @typescript-eslint/no-misused-promises */}
          {/* eslint-disable react/jsx-handler-names */}
          <button onClick={this.newGame} className='button'>New Game</button>
          <button onClick={this.endBuild} className='button'>Skip Build</button>
        </div>
      </div>
    )
  }
}

export default App
