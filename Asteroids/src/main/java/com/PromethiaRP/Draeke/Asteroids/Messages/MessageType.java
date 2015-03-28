package com.PromethiaRP.Draeke.Asteroids.Messages;

public enum MessageType {
ACTION_SHOOT,		// Goes with ActionMessage

COUNTDOWN_BEGIN,	// Goes with CountdownMessage
COUNTDOWN_FINISH,	// Goes with CountdownMessage
COUNTDOWN_PAUSE,	// Goes with CountdownMessage
COUNTDOWN_RESUME,	// Goes with CountdownMessage
COUNTDOWN_STOP,		// Goes with CountdownMessage

ENTITY_COLLIDE,		// Goes with CollisionMessage
ENTITY_DIE,			// Goes with EntityDieMessage

HURT_INTERACTION,
HURT_ENVIRONMENT,

IMPULSE,
MOVE,
RENDER,
UPDATE_INPUT,		// Goes with UpdateMessage
UPDATE_DELTA,		// Goes with UpdateMessage
WEAPON_FIRE;
}
