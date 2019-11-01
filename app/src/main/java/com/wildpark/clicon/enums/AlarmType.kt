package com.wildpark.clicon.enums

enum class AlarmType {
    WATER {
        override fun getSuperType(): AlarmType {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            return this
        }

        override fun isCritical(): Boolean {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            return true
        }

        override fun isTemporary(): Boolean {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            return true
        }

    },

    SCREW {
        override fun getSuperType(): AlarmType {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            return this
        }

        override fun isCritical(): Boolean {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            return true
        }

        override fun isTemporary(): Boolean {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            return true
        }

    },

    MODULE {
        override fun getSuperType(): AlarmType {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            return this
        }

        override fun isCritical(): Boolean {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            return true
        }

        override fun isTemporary(): Boolean {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            return true
        }
    },

    MODULE_CRM311 {
        override fun getSuperType(): AlarmType {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            return this
        }

        override fun isCritical(): Boolean {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            return true
        }

        override fun isTemporary(): Boolean {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            return true
        }
    },

    MODULE_CRM310 {
        override fun getSuperType(): AlarmType {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            return this
        }

        override fun isCritical(): Boolean {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            return true
        }

        override fun isTemporary(): Boolean {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            return true
        }
    },

    MODULE_MM311 {
        override fun getSuperType(): AlarmType {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            return this
        }

        override fun isCritical(): Boolean {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            return true
        }

        override fun isTemporary(): Boolean {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            return true
        }
    },

    MODULE_PRM311 {
        override fun getSuperType(): AlarmType {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            return this
        }

        override fun isCritical(): Boolean {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            return true
        }

        override fun isTemporary(): Boolean {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            return true
        }
    },

    SENSOR {
        override fun getSuperType(): AlarmType {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            return this
        }

        override fun isCritical(): Boolean {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            return true
        }

        override fun isTemporary(): Boolean {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            return true
        }
    },

    FLOOD {
        override fun getSuperType(): AlarmType {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            return this
        }

        override fun isCritical(): Boolean {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            return true
        }

        override fun isTemporary(): Boolean {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            return true
        }
    },

    SMOKE {
        override fun getSuperType(): AlarmType {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            return this
        }

        override fun isCritical(): Boolean {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            return true
        }

        override fun isTemporary(): Boolean {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            return true
        }
    },

    ATTENUATION {
        override fun getSuperType(): AlarmType {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            return this
        }

        override fun isCritical(): Boolean {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            return true
        }

        override fun isTemporary(): Boolean {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            return true
        }
    };

    abstract fun getSuperType(): AlarmType

    abstract fun isCritical(): Boolean

    abstract fun isTemporary(): Boolean

    fun isPermanent(): Boolean {
        return !isTemporary()
    }
}