import React, { useState } from 'react';
import { motion, AnimatePresence } from 'framer-motion';
import { AuthWrapper } from '../../styles/AuthStyles.js';
import LoginForm from './LoginForm';
import SignUpForm from './SignUpForm';


export default function AuthForm() {

    const [formType, setFormType] = useState('login'); // 'login' | 'signup'

    const toggleForm = () => {
        setFormType((prev) => (prev === 'login' ? 'signup' : 'login'));
    };

    return (
            <AnimatePresence mode="wait">
                {formType === 'login' ? (
                    <motion.div
                        key="login"
                        initial={{ opacity: 0, y: 30 }}
                        animate={{ opacity: 1, y: 0 }}
                        exit={{ opacity: 0, y: -30 }}
                        transition={{ duration: 0.3 }}
                    >
                        <LoginForm onSwitch={toggleForm} />
                    </motion.div>
                ) : (
                    <motion.div
                        key="signup"
                        initial={{ opacity: 0, y: 30 }}
                        animate={{ opacity: 1, y: 0 }}
                        exit={{ opacity: 0, y: -30 }}
                        transition={{ duration: 0.3 }}
                    >
                        <SignUpForm onSwitch={toggleForm} />
                    </motion.div>
                )}
            </AnimatePresence>
    );
}
