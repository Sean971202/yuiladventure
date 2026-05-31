// 1. 設定畫布
const canvas = document.createElement('canvas');
canvas.width = 800;
canvas.height = 600;
canvas.style.backgroundColor = '#d4e157'; 
document.body.appendChild(canvas);
const ctx = canvas.getContext('2d');

// 2. 玩家與資料結構
let player = { x: 400, y: 300, color: 'blue', size: 30 };
let npcs = [
    { x: 100, y: 100, dx: 2, dy: 1, color: 'red' },
    { x: 600, y: 400, dx: -1, dy: 2, color: 'purple' }
];

// 3. 與 Java 後端連接的測試函式
async function fetchStoryFromJava() {
    try {
        const response = await fetch('http://localhost:8000/api/location');
        const data = await response.json();
        console.log("成功連結 Java 後端，收到資料:", data);
        alert("從玉里伺服器收到故事: " + data.story);
    } catch (error) {
        console.log("請先啟動 Java 伺服器 (java Game)");
    }
}

// 4. 遊戲渲染迴圈
function update() {
    ctx.clearRect(0, 0, canvas.width, canvas.height);

    // 畫出 NPC
    npcs.forEach(npc => {
        ctx.fillStyle = npc.color;
        ctx.fillRect(npc.x, npc.y, 20, 20);
        npc.x += npc.dx; npc.y += npc.dy;
        if (npc.x < 0 || npc.x > 780) npc.dx *= -1;
        if (npc.y < 0 || npc.y > 580) npc.dy *= -1;
    });

    // 畫出玩家
    ctx.fillStyle = player.color;
    ctx.fillRect(player.x, player.y, player.size, player.size);

    requestAnimationFrame(update);
}

// 5. 鍵盤控制
document.addEventListener('keydown', (e) => {
    if(e.key === 'ArrowRight') player.x += 15;
    if(e.key === 'ArrowLeft') player.x -= 15;
    if(e.key === 'ArrowUp') player.y -= 15;
    if(e.key === 'ArrowDown') player.y += 15;
    
    // 按下空白鍵測試連線
    if(e.key === ' ') fetchStoryFromJava();
});

update();