import pygame as pg
from Collision import CollisionObserver
from Animation import Animation
from Weapon import Weapon

class Player(CollisionObserver):

    def __init__(self, x, y, w, h, speedMax):
        self.x              = x
        self.y              = y
        self.w              = w
        self.h              = h
        self.speedMax       = speedMax
        self.left           = False
        self.right          = False
        self.up             = False
        self.down           = False
        self.jumpCounter    = 0
        self.jumping        = False
        self.falling        = True
        self.fallSpeed      = 6

        self.leftBlocked    = False
        self.upBlocked      = False
        self.rightBlocked   = False
        self.downBlocked    = False

        self.collided       = False
        self.canJump        = False

        #NEEWWW
        #ANIMATIONEN
        self.animationRight = Animation(25, ('res/img/player/right_1.png','res/img/player/right_2.png','res/img/player/right_3.png'))
        self.animationLeft  = Animation(25, ('res/img/player/left_1.png','res/img/player/left_2.png','res/img/player/left_3.png')) 
        self.animationIdleRight  = Animation(9, ('res/img/player/idle_r_1.png','res/img/player/idle_r_2.png'))
        self.animationIdleLeft  = Animation(9, ('res/img/player/idle_l_1.png','res/img/player/idle_l_2.png'))
        #LIST OF IDLE ANIMATIONS
        self.idle           = (self.animationIdleRight, self.animationIdleLeft)
        #DIRECTION THE CHARACTER LOOKS
        self.direction      = 0 # 0 = rechts     1 = links
        #CURRENT ANIMATION
        self.currentAni     = self.animationIdleRight
        #WEAPON
        self.weapon = Weapon(self.x, self.y)
        self.armed = False
        
    #NEWWW
    def changeDir(self, dir):
        self.direction = dir
    def equip(self, weapon):
        self.weapon = weapon
        self.armed = True
        

    def draw(self, screen):
        self.currentAni.draw(screen, self.x, self.y)

    #NEWWW
    def seedFps(self, fps):
        self.animationRight.calcToken(fps)
        self.animationLeft.calcToken(fps)
        for a in self.idle:
            a.calcToken(fps)
    #NEEW
    def notify(self, currentFps):
        self.currentAni.notify(currentFps)
        

    def move(self):
        self.currentAni = self.idle[self.direction]
        if self.armed:
            (self.weapon).moveWeapon(self.x + 3, self.y + 17)
            self.weapon.changeDir(self.direction)

        if self.downBlocked == False and self.jumping == False:
            self.y += self.fallSpeed
            self.falling = True
        else:
            self.falling = False
        if self.left and self.right:
            self.x += 0
        if self.left:
            self.direction = 1
            self.currentAni = self.animationLeft
            if self.leftBlocked == False:
                self.x -= self.speedMax
        if self.right:
            self.direction = 0
            self.currentAni = self.animationRight
            if self.rightBlocked == False:
                self.x += self.speedMax 
        if self.up:
            self.jumping = True
            self.jump()

    def update(self):
        self.move()
        #print "Falling:", self.falling, "\nLeft blocked:", self.leftBlocked, "\nRight blocked:", self.rightBlocked
        self.leftBlocked = self.rightBlocked = self.downBlocked = False

    def checkCollision(self):
        return

    def jump(self):
        if self.jumpCounter < 20:
            self.falling = False
            self.jumpCounter += 1
            self.y -= 6
        else:
            self.jumping = False
            self.falling = True
            self.up = False
            self.jumpCounter = 0

    def setLeft(self, b):
        self.left = b

    def setRight(self, b):
        self.right = b

    def setUp(self, b):
        self.up = b
        self.canJump = False

    def setDown(self, b):
        self.down = b

    def isJumping(self):
        return self.jumping

    def isFalling(self):
        return self.falling

    def collidePlayer(self, player):
        #print "Player blocks Block"
        return

    def collideProjectile(self, thing):
        #print "Player blocks Projectile"
        return

    def collideElse(self, thing):
        rect = thing.getRect()
        rectL = (rect.x, rect.y, self.speedMax, rect.h)
        rectR = (rect.x + rect.w - self.speedMax, rect.y, self.speedMax, rect.h)
        rectU = (rect.x, rect.y, rect.w, self.fallSpeed)
        playerRect = self.getRect()
        if playerRect.move(0, self.fallSpeed).colliderect(rectU):
            self.downBlocked = True
            self.y -= abs((self.y + self.h) - rect.y)
            self.canJump = True
        else:
            self.downBlocked = False
        if self.getRect().move(-self.speedMax, 0).colliderect(rectR):
            self.leftBlocked = True
            self.x += abs(self.x - (rect.x + rect.w))
        if self.getRect().move(self.speedMax, 0).colliderect(rectL):
            self.rightBlocked = True
            self.x -= abs((self.x + self.w) - rect.x)

    def getRect(self):
        return pg.Rect(self.x, self.y, self.w, self.h)

    def getCanJump(self):
        return self.canJump