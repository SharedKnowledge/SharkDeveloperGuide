import pygame as pg

from KBH                import KeyboardHandler
from Dude               import dude
from Animation          import Animation

from Player             import Player
from AnimationHandler   import AnimationHandler

from CollisionHandler   import CollisionHandler

from Terrain            import Terrain
from Block 				import Block
from DrawHandler		import DrawHandler
class StuffFactory():
	def __init__ (self, screen, FPS):
		self.anHa = AnimationHandler(FPS, screen)
		self.coHa = CollisionHandler()
		self.daHa = DrawHandler(screen)

	def updateView(self, currentFPS, player):
		self.anHa.notifyObserver(currentFPS)
		for ps in player:
			self.coHa.checkOCollision(ps)	
		self.daHa.drawObserver()

	def createAnimation(self, images):
		a = Animation(self.FPS, images)
		self.anHa.addObserver(a)
		self.daHa.addObserver(a)
		return a

	def createTerrain(self, x, y, imagePath, collidable):
		t = Terrain(x,y,imagePath)
		self.coHa.addObserver(t)
		self.daHa.addObserver(t)
		

	def createPlayer(self, x, y, w, h, speed, number):
		self.p = Player( x, y, w, h, speed)
		self.coHa.addPlayer(self.p)
		self.daHa.addObserver(self.p)
		self.anHa.addObserver(self.p)
		return self.p

	def createBlock(self, x, y):
		b = Block(x,y)
		self.coHa.addObserver(b)
		self.anHa.addObserver(b)
		self.daHa.addObserver(b)
