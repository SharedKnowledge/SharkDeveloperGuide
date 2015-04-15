import pygame as pg

from KBH                import KeyboardHandler
from Dude               import dude
from Animation          import Animation

from Player             import Player
from AnimationHandler   import AnimationHandler
#from Sound              import Mixer

from CollisionHandler   import CollisionHandler
from Block              import Block
from Terrain            import Terrain

from SFactory           import StuffFactory
from DrawHandler		import DrawHandler
from Weapon             import Weapon
from Menu               import Menu
pg.init()

#COLORS
white = (255,255,255)
black = (0,0,0)
weird = (172,23,98)
FPS = 60
clock = pg.time.Clock()

rec_x =100
rec_y = 100
tup =(0,0);
wth = 2
hgt = 12
HEIGHT = 600
WIDTH  = 1000

#screen
gameScreen = pg.display.set_mode((WIDTH, HEIGHT))
pg.display.set_caption('Testerino')
players = []
sf = StuffFactory(gameScreen, FPS)
sf.createTerrain(0,550, 'res/img/ug.png', True)

m = Menu()

player = sf.createPlayer(200, 100, 25, 50, 5)
weapon = sf.createWeapon(1, 1)

player.equip(weapon)

players.append(player)
sf.createBlock(100,450)
sf.createBlock(500,450)

#KBH
kbh = KeyboardHandler(player, 0)


#annimation
duder = Animation(5, ('res/img/dudd.jpg','res/img/dudd2.jpg'))


running = True


counter = 0
while running:
    counter += 1 
    for event in pg.event.get():
        kbh.checkKeys(event)
    #update
    
    for p in players:
        p.update()  
    #player.update()
    #splayer2.update()
    #render
    gameScreen.fill(white)
    sf.updateView(counter, players)
    
    
    #refresh
    if counter == FPS:
        counter = 0
    pg.display.flip()
    clock.tick(FPS)

#exit
pg.quit()
quit()
