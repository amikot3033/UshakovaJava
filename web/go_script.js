document.addEventListener('DOMContentLoaded', () => {
    const boardElement = document.getElementById('game-board');
    const currentPlayerElement = document.getElementById('current-player');

    const BOARD_SIZE = 20;
    function initializeBoard() {
        boardElement.innerHTML = '';
        for (let i = 0; i < BOARD_SIZE; i++) {
            const row = document.createElement('div');
            row.classList.add('row');
            for (let j = 0; j < BOARD_SIZE; j++) {
                const cell = document.createElement('div');
                cell.classList.add('cell');
                cell.dataset.x = i;
                cell.dataset.y = j;
                cell.addEventListener('click', () => makeMove(i, j));
                row.appendChild(cell);
            }
            boardElement.appendChild(row);
        }
    }

    async function makeMove(x, y) {
        try {
            const response = await fetch('/move', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded',
                },
                body: `x=${x}&y=${y}`
            });

            if (response.ok) {
                const data = await response.json();
                if (data.message) {
                    alert(data.message);
                }
                updateState();
            } else {
                const error = await response.json();
                alert(error.error || 'Ошибка отправки хода');
            }
        } catch (err) {
            console.error('Ошибка:', err);
            alert('Произошла ошибка связи с сервером.');
        }
    }

    async function updateState() {
        try {
            const response = await fetch('/state');
            if (response.ok) {
                const data = await response.json();
                renderBoard(data.board);
                currentPlayerElement.textContent = `Ход игрока: ${data.currentPlayer}`;
            } else {
                console.error('Ошибка обновления:', await response.text());
            }
        } catch (err) {
            console.error('Ошибка:', err);
        }
    }

    function renderBoard(board) {
        const cells = document.querySelectorAll('.cell');
        for (let i = 0; i < BOARD_SIZE; i++) {
            for (let j = 0; j < BOARD_SIZE; j++) {
                const cell = cells[i * BOARD_SIZE + j];
                cell.textContent = board[i][j] !== ' ' ? board[i][j] : '';
                cell.classList.toggle('occupied', board[i][j] !== ' ');
            }
        }
    }
    initializeBoard();
    updateState();

    setInterval(updateState, 2000);
});
