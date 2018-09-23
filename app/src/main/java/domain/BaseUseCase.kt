package domain

import Coroutines.AsyncTasksManager

abstract class BaseUseCase
constructor(asyncTasksManager: AsyncTasksManager) : AsyncTasksManager by asyncTasksManager