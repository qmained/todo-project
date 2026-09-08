package org.qmained.todoproject.exceptions

import java.util.UUID

class TodoNotFound(val id: UUID) : Exception()
