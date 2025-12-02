import { ArrowLeft, Save } from 'lucide-react';

interface UpdateProfileScreenProps {
  onNavigate: (screen: string) => void;
}

export function UpdateProfileScreen({ onNavigate }: UpdateProfileScreenProps) {
  return (
    <div className="min-h-screen bg-gradient-to-br from-blue-50 via-purple-50 to-green-50 p-4 py-8">
      <div className="max-w-2xl mx-auto">
        {/* Header */}
        <div className="flex items-center gap-4 mb-6">
          <button 
            onClick={() => onNavigate('perfil')}
            className="p-2 hover:bg-white rounded-xl transition-colors"
          >
            <ArrowLeft className="w-6 h-6 text-gray-600" />
          </button>
          <h1 className="text-gray-800">Atualizar Dados</h1>
        </div>

        {/* Form Card */}
        <div className="bg-white rounded-3xl shadow-xl p-8">
          <div className="space-y-5">
            {/* Nome */}
            <div>
              <label className="block text-gray-700 mb-2">Nome Completo</label>
              <input
                type="text"
                defaultValue="Ana Maria Silva"
                className="w-full px-4 py-3 border-2 border-gray-200 rounded-xl focus:border-blue-300 focus:outline-none transition-colors"
              />
            </div>

            {/* Data de Nascimento */}
            <div>
              <label className="block text-gray-700 mb-2">Data de Nascimento</label>
              <input
                type="date"
                defaultValue="1985-03-15"
                className="w-full px-4 py-3 border-2 border-gray-200 rounded-xl focus:border-blue-300 focus:outline-none transition-colors"
              />
            </div>

            {/* Email */}
            <div>
              <label className="block text-gray-700 mb-2">Email</label>
              <input
                type="email"
                defaultValue="ana.silva@email.com"
                className="w-full px-4 py-3 border-2 border-gray-200 rounded-xl focus:border-blue-300 focus:outline-none transition-colors"
              />
            </div>

            {/* Senha */}
            <div>
              <label className="block text-gray-700 mb-2">Nova Senha (deixe em branco para manter)</label>
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
                defaultValue="(11) 98765-4321"
                className="w-full px-4 py-3 border-2 border-gray-200 rounded-xl focus:border-blue-300 focus:outline-none transition-colors"
              />
            </div>

            {/* Endereço */}
            <div>
              <label className="block text-gray-700 mb-2">Endereço</label>
              <textarea
                defaultValue="Rua das Flores, 123 - Centro, São Paulo - SP"
                rows={3}
                className="w-full px-4 py-3 border-2 border-gray-200 rounded-xl focus:border-blue-300 focus:outline-none transition-colors resize-none"
              />
            </div>

            {/* Action Buttons */}
            <div className="flex gap-3 pt-4">
              <button 
                onClick={() => onNavigate('perfil')}
                className="flex-1 bg-white border-2 border-gray-200 text-gray-700 py-4 rounded-xl hover:bg-gray-50 transition-all"
              >
                Voltar
              </button>
              <button className="flex-1 bg-gradient-to-r from-blue-300 to-purple-300 text-gray-800 py-4 rounded-xl hover:from-blue-400 hover:to-purple-400 transition-all shadow-md hover:shadow-lg flex items-center justify-center gap-2">
                <Save className="w-5 h-5" />
                Salvar Alterações
              </button>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}
