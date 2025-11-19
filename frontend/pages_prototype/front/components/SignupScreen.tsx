import { Heart, ArrowLeft } from 'lucide-react';

interface SignupScreenProps {
  onNavigate: (screen: string) => void;
}

export function SignupScreen({ onNavigate }: SignupScreenProps) {
  return (
    <div className="min-h-screen bg-gradient-to-br from-blue-50 via-purple-50 to-green-50 p-4 py-8">
      <div className="max-w-2xl mx-auto">
        {/* Header */}
        <div className="flex items-center gap-4 mb-6">
          <button 
            onClick={() => onNavigate('login')}
            className="p-2 hover:bg-white rounded-xl transition-colors"
          >
            <ArrowLeft className="w-6 h-6 text-gray-600" />
          </button>
          <div className="bg-gradient-to-br from-blue-200 to-purple-200 p-3 rounded-full">
            <Heart className="w-8 h-8 text-purple-600" />
          </div>
          <div>
            <h1 className="text-gray-800">easypeasy</h1>
            <p className="text-gray-600">Cadastro de Usuário</p>
          </div>
        </div>

        {/* Form Card */}
        <div className="bg-white rounded-3xl shadow-xl p-8">
          <div className="space-y-5">
            {/* Nome */}
            <div>
              <label className="block text-gray-700 mb-2">Nome Completo</label>
              <input
                type="text"
                placeholder="Digite seu nome"
                className="w-full px-4 py-3 border-2 border-gray-200 rounded-xl focus:border-blue-300 focus:outline-none transition-colors"
              />
            </div>

            {/* Data de Nascimento */}
            <div>
              <label className="block text-gray-700 mb-2">Data de Nascimento</label>
              <input
                type="date"
                className="w-full px-4 py-3 border-2 border-gray-200 rounded-xl focus:border-blue-300 focus:outline-none transition-colors"
              />
            </div>

            {/* Email */}
            <div>
              <label className="block text-gray-700 mb-2">Email</label>
              <input
                type="email"
                placeholder="seu@email.com"
                className="w-full px-4 py-3 border-2 border-gray-200 rounded-xl focus:border-blue-300 focus:outline-none transition-colors"
              />
            </div>

            {/* Senha */}
            <div>
              <label className="block text-gray-700 mb-2">Senha</label>
              <input
                type="password"
                placeholder="••••••••"
                className="w-full px-4 py-3 border-2 border-gray-200 rounded-xl focus:border-blue-300 focus:outline-none transition-colors"
              />
            </div>

            {/* Telefone */}
            <div>
              <label className="block text-gray-700 mb-2">Telefone</label>
              <input
                type="tel"
                placeholder="(00) 00000-0000"
                className="w-full px-4 py-3 border-2 border-gray-200 rounded-xl focus:border-blue-300 focus:outline-none transition-colors"
              />
            </div>

            {/* Endereço */}
            <div>
              <label className="block text-gray-700 mb-2">Endereço</label>
              <textarea
                placeholder="Rua, número, bairro, cidade"
                rows={3}
                className="w-full px-4 py-3 border-2 border-gray-200 rounded-xl focus:border-blue-300 focus:outline-none transition-colors resize-none"
              />
            </div>

            {/* Tipo de Usuário */}
            <div>
              <label className="block text-gray-700 mb-3">Tipo de Usuário</label>
              
              {/* Info Banner */}
              <div className="bg-blue-50 border-2 border-blue-200 rounded-xl p-3 mb-3">
                <p className="text-blue-800 text-sm flex items-start gap-2">
                  <span>ℹ️</span>
                  <span><strong>Idosos</strong> criam e gerenciam listas. <strong>Familiares</strong> visualizam e atualizam status.</span>
                </p>
              </div>

              <div className="space-y-3">
                <label className="flex items-center gap-3 p-4 border-2 border-gray-200 rounded-xl cursor-pointer hover:border-blue-300 transition-colors">
                  <input type="radio" name="userType" value="idoso" className="w-5 h-5 text-blue-300" />
                  <div>
                    <div className="text-gray-800">Idoso</div>
                    <div className="text-gray-500 text-sm">Criar e gerenciar minhas listas de desejos</div>
                  </div>
                </label>

                <label className="flex items-center gap-3 p-4 border-2 border-gray-200 rounded-xl cursor-pointer hover:border-purple-300 transition-colors">
                  <input type="radio" name="userType" value="familiar" className="w-5 h-5 text-purple-300" />
                  <div>
                    <div className="text-gray-800">Familiar</div>
                    <div className="text-gray-500 text-sm">Visualizar listas e atualizar status dos itens</div>
                  </div>
                </label>

                <label className="flex items-center gap-3 p-4 border-2 border-gray-200 rounded-xl cursor-pointer hover:border-green-300 transition-colors">
                  <input type="radio" name="userType" value="admin" className="w-5 h-5 text-green-300" />
                  <div>
                    <div className="text-gray-800">Administrador</div>
                    <div className="text-gray-500 text-sm">Gerenciar usuários do sistema</div>
                  </div>
                </label>
              </div>
            </div>

            {/* Submit Button */}
            <button className="w-full bg-gradient-to-r from-blue-300 to-purple-300 text-gray-800 py-4 rounded-xl hover:from-blue-400 hover:to-purple-400 transition-all shadow-md hover:shadow-lg mt-6">
              Criar Conta
            </button>
          </div>
        </div>
      </div>
    </div>
  );
}
