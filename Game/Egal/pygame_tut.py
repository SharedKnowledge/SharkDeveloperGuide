import pygame as pg

from KBH                import KeyboardHandler
from Dude               import dude
from Animation          import Animation

from Player             import Player
from AnimationHandler   import AnimationHandler
from Sound              import Mixer

from CollisionHandler   import CollisionHandler
from Block              import Block
from Terrain            import Terrain

from SFactory           import StuffFactory
from DrawHandler		import DrawHandler
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

#screen
gameScreen = pg.display.set_mode((1000,800))
pg.display.set_caption('Testerino')

sf = StuffFactory(gameScreen, FPS)
sf.createTerrain(0,750, 'res/img/ug.png', True)



player = sf.createPlayer(200, 100, 25, 50, 5,1)
player2 = sf.createPlayer(300, 100, 25, 50, 5,1)
sf.createBlock(100,650)
sf.createBlock(500,650)

#KBH
kbh = KeyboardHandler(player, 2)
kbh2 = KeyboardHandler(player2, 1)

#annimation
duder = Animation(5, ('res/img/dudd.jpg','res/img/dudd2.jpg'))


#music
mixer = Mixer()
mixer.load('res/sound/thundah.wav')
#mixer.loop()

running = True


counter = 0
while running:
    counter += 1 
    for event in pg.event.get():
        kbh.checkKeys(event)
        kbh2.checkKeys(event)

    #update
    
      
    player.update()
    player2.update()
    #render
    gameScreen.fill(white)
    sf.updateView(counter, (player,player2))
    
    
    
    
    #refresh
    if counter == FPS:
        counter = 0
    pg.display.flip()
    clock.tick(FPS)

#exit
pg.quit()
quit()
