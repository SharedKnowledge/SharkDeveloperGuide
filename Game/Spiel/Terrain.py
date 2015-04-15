import pygame as pg
from Collision import CollisionObserver
class Terrain(CollisionObserver):
	def __init__ (self, x, y, imagePath):
		self.image = pg.image.load(imagePath)
		self.x = x
		self.y = y
		self.rect = pg.Rect(self.x , self.y, self.image.get_width(), self.image.get_height())
	def getRect(self):
		return self.rect

	def draw(self, screen):
		screen.blit(self.image, (self.x,self.y))