import { Heart, Mail, Lock, UserPlus, LogOut } from 'lucide-react';

interface LoginScreenProps {
  onNavigate: (screen: string) => void;
}

export function LoginScreen({ onNavigate }: LoginScreenProps) {
  return (
    <div className="min-h-screen bg-gradient-to-br from-blue-50 via-purple-50 to-green-50 flex items-center justify-center p-4">
      <div className="bg-white rounded-3xl shadow-xl p-8 w-full max-w-md">
        {/* Logo */}
        <div className="flex flex-col items-center mb-8">
          <div className="bg-gradient-to-br from-blue-200 to-purple-200 p-4 rounded-full mb-4">
            <Heart className="w-12 h-12 text-purple-600" />
          </div>
          <h1 className="text-gray-800 text-center">easypeasy</h1>
          <p className="text-gray-500 text-center mt-2">Conectando famílias com carinho</p>
        </div>

        {/* Form */}
        <div className="space-y-4">
          <div>
            <label className="block text-gray-700 mb-2">Email</label>
            <div className="relative">
              <Mail className="absolute left-3 top-1/2 transform -translate-y-1/2 text-gray-400 w-5 h-5" />
              <input
                type="email"
                placeholder="seu@email.com"
                className="w-full pl-12 pr-4 py-3 border-2 border-gray-200 rounded-xl focus:border-blue-300 focus:outline-none transition-colors"
              />
            </div>
          </div>

          <div>
            <label className="block text-gray-700 mb-2">Senha</label>
            <div className="relative">
              <Lock className="absolute left-3 top-1/2 transform -translate-y-1/2 text-gray-400 w-5 h-5" />
              <input
                type="password"
                placeholder="••••••••"
                className="w-full pl-12 pr-4 py-3 border-2 border-gray-200 rounded-xl focus:border-blue-300 focus:outline-none transition-colors"
              />
            </div>
          </div>

          {/* Primary Button */}
          <button 
            onClick={() => onNavigate('home-familiar')}
            className="w-full bg-gradient-to-r from-blue-300 to-purple-300 text-gray-800 py-4 rounded-xl hover:from-blue-400 hover:to-purple-400 transition-all shadow-md hover:shadow-lg mt-6"
          >
            Entrar
          </button>

          {/* Secondary Button */}
          <button 
            onClick={() => onNavigate('cadastro')}
            className="w-full bg-white border-2 border-purple-200 text-gray-700 py-4 rounded-xl hover:bg-purple-50 transition-all flex items-center justify-center gap-2"
          >
            <UserPlus className="w-5 h-5" />
            Cadastre-se
          </button>
        </div>

        {/* Footer */}
        <div className="mt-8 text-center space-y-2">
          <button 
            onClick={() => onNavigate('guia-permissoes')}
            className="text-purple-500 hover:text-purple-700 transition-colors text-sm"
          >
            Ver guia de permissões
          </button>
          <div>
            <button className="text-gray-400 hover:text-gray-600 transition-colors flex items-center justify-center gap-2 mx-auto">
              <LogOut className="w-4 h-4" />
              <span className="text-sm">Sair</span>
            </button>
          </div>
        </div>
      </div>
    </div>
  );
}
