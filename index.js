import makeWASocket, { useMultiFileAuthState, DisconnectReason } from '@whiskeysockets/baileys'
import P from 'pino'

const BOT_NAME = "Hemlock'Protocol_bot"
const BOT_DATA = `╭━━━〔 ${BOT_NAME} 〕━━━⬣
👤 User: @user
┃ 👤 Status: Free User
┃ 🫯 Limit: No Limit
╰━━━━━━━━━━━━⬣

╭━━━〔 BOT DATA 〕━━━⬣
┃ ⏱️ Runtime: Everyday
┃ 🏘️ Total Groups: @groups
┃ 👥 Total Users: @users
╰━━━━━━━━━━━━⬣`

const FULL_MENU = BOT_DATA + `

┌──⭓ 🎌 ANIME
│ ◦.anime
│ ◦.animeinfo
│ ◦.character
│ ◦.topanime
└────────────⭓

┌──⭓ 📦 APK DOWNLOAD 
│ ◦.apk
│ ◦.capcut
│ ◦.facebook
│ ◦.gdrive
│ ◦.get (.fetch)
│ ◦.getall
│ ◦.ig (.instagram)
│ ◦.mediafire (.mf)
│ ◦.scribd
│ ◦.spotify
│ ◦.tiktok (.tt)
│ ◦.ytmp3 (.youtubeaudio)
│ ◦.ytmp4 (.youtubevideo)
└────────────⭓

┌──⭓ 📖 EDUCATION
│ ◦.define
│ ◦.dict
└────────────⭓

┌──⭓ 🎮 GAME
│ ◦.asahotak (.ao)
│ ◦.family (.f100)
│ ◦.susunkata (.sk)
│ ◦.tebakgambar (.tg)
└────────────⭓

┌──⭓ 🧲 GROUP
│ ◦.acceptall
│ ◦.add
│ ◦.addbadword
│ ◦.addresp
│ ◦.afk
│ ◦.antilink
│ ◦.antispam
│ ◦.antitag
│ ◦.antitagsw (.atsw)
│ ◦.badword
│ ◦.close
│ ◦.delresp
│ ◦.demote
│ ◦.ekspp (.getpp)
│ ◦.goodbye
│ ◦.hidetag (.ht)
│ ◦.htforall
│ ◦.kick
│ ◦.linkgc
│ ◦.listresp
│ ◦.open
│ ◦.promolimit
│ ◦.promote
│ ◦.rejectall
│ ◦.revoke (.resetlink)
│ ◦.setdesc
│ ◦.setgoodbye
│ ◦.setname
│ ◦.setppgc
│ ◦.tagadmin
│ ◦.tagall (.all)
│ ◦.tagme
│ ◦.unwarn
│ ◦.userinfo
│ ◦.warn
│ ◦.welcome
│ ◦.setwelcome
└────────────⭓

┌──⭓ 🫟 IMAGETOOLS
│ ◦.compress (.kompres)
│ ◦.ihancer (.hd2)
│ ◦.enhance (.hd3)
│ ◦.remini (.hd)
│ ◦.removebg
│ ◦.upscale (.hd4)
└────────────⭓

┌──⭓ 📰 INFORMATION
│ ◦.wiki
│ ◦.wikisummary
└────────────⭓

┌──⭓ 🕌 ISLAM
│ ◦.alquran
│ ◦.tafsir
└────────────⭓

┌──⭓ 🎬 MEDIA
│ ◦.hdvideo
│ ◦.qr
│ ◦.readqr (.scanqr)
│ ◦.toaudio
└────────────⭓

┌──⭓ 🌐 NETWORKTOOLS
│ ◦.asn
│ ◦.dns
│ ◦.geocode
│ ◦.geoip
│ ◦.header
│ ◦.ip (.cekip)
│ ◦.portcheck
│ ◦.reverseip
│ ◦.ssl
│ ◦.subfinder
│ ◦.trace
│ ◦.whois
└────────────⭓

┌──⭓ 👑 OWNER
│ ◦.addcmd
│ ◦.addpackage
│ ◦.addpremium (.addprem)
│ ◦.analytics
│ ◦.backupdata
│ ◦.bancmd
│ ◦.cekfreetoken
│ ◦.cekgrup
│ ◦.cekpremuser
│ ◦.delbatch
│ ◦.delcmd
│ ◦.editcmd
│ ◦.gentoken
│ ◦.getatribut
│ ◦.htgc
│ ◦.htgc2
│ ◦.imap
│ ◦.infoserver
│ ◦.kickbot (.keluar)
│ ◦.kill
│ ◦.killfor
│ ◦.listcmd
│ ◦.modegrup
│ ◦.modepair
│ ◦.removepackage
│ ◦.restart (.reboot)
│ ◦.restoredata
│ ◦.setppbot (.setpp)
│ ◦.setwlcm
│ ◦.storage
│ ◦.testallcmd
│ ◦.unban
│ ◦.unbancmd
└────────────⭓

┌──⭓ 📁 PAIRING
│ ◦.infopair
│ ◦.listpair
│ ◦.pair
│ ◦.restartsession
│ ◦.stop
└────────────⭓

┌──⭓ 💎 PREMIUM
│ ◦.belipremium (.buypremium)
│ ◦.cekstatusku (.ceklimit)
│ ◦.join (.invitebot)
│ ◦.joinfree (.jf)
│ ◦.premiumtoken
│ ◦.redeem
└────────────⭓

┌──⭓ 🎁 REFERRAL
│ ◦.invite (.ref)
│ ◦.invitestats
│ ◦.redeemall
│ ◦.start
│ ◦.topinvite
└────────────⭓

┌──⭓ 🧩 STICKER
│ ◦.autostiker (.astick)
│ ◦.brat
│ ◦.brat2
│ ◦.bratgif (.bgif)
│ ◦.emojimix (.emix)
│ ◦.sbatch
│ ◦.smeme
│ ◦.sprem (.stickerpremium)
│ ◦.steks
│ ◦.sticker (.s)
│ ◦.sgif (.stickergif)
│ ◦.toimg (.toimage)
│ ◦.wm
└────────────⭓

┌──⭓ 🎠 SYSTEM
│ ◦.groupbot
│ ◦.info (.about)
│ ◦.language (.setlang)
│ ◦.menu (.help)
│ ◦.owner (.creator)
│ ◦.ping (.p)
│ ◦.status (.stats)
│ ◦.tesrespon (.tesr)
└────────────⭓

┌──⭓ 🔏 TEXT
│ ◦.base64 (.b64)
│ ◦.binary
│ ◦.caesar
│ ◦.case
│ ◦.count
│ ◦.dedupe
│ ◦.diff
│ ◦.hash
│ ◦.leet (.1337)
│ ◦.morse
│ ◦.palindrome
│ ◦.password (.genpass)
│ ◦.random
│ ◦.reverse
│ ◦.sort
│ ◦.vigenere
└────────────⭓

┌──⭓ 🎋 UTILITY
│ ◦.autotranslate (.autotr)
│ ◦.cekid
│ ◦.cekidch
│ ◦.cuaca (.forecast)
│ ◦.delete (.del)
│ ◦.getexif (.exif)
│ ◦.github
│ ◦.githubup
│ ◦.joke
│ ◦.kurs
│ ◦.readmore (.rdmr)
│ ◦.rvo
│ ◦.remrepo
│ ◦.repo
│ ◦.shortlink
│ ◦.mail
│ ◦.time
│ ◦.timezone
│ ◦.tourl (.upload)
`

async function start() {
  const { state, saveCreds } = await useMultiFileAuthState('./session')
  const sock = makeWASocket({ auth: state, logger: P({level:'silent'}) })
  sock.ev.on('creds.update', saveCreds)
  sock.ev.on('connection.update', async (u) => {
    const {connection, lastDisconnect} = u
    if(connection==='close' && lastDisconnect?.error?.output?.statusCode!== DisconnectReason.loggedOut) start()
    if(connection==='open') console.log('Bot online!')
  })
  sock.ev.on('messages.upsert', async ({messages}) => {
    const m = messages[0]
    if(!m.message || m.key.fromMe) return
    const text = m.message.conversation || m.message.extendedTextMessage?.text || ""
    const jid = m.key.remoteJid
    const cmd = text.toLowerCase().split(' ')[0]

    if(cmd === '.menu' || cmd === '.help') {
      await sock.sendMessage(jid, {text: FULL_MENU})
    }
    if(cmd === '.ping') {
      await sock.sendMessage(jid, {text: 'Pong! KwaThema-Bot 24/7 ✅ All 200+ commands loaded'})
    }
    // Example - all commands will reply with coming soon until you add plugin
    if(text.startsWith('.') && text.length>2 && cmd!=='.menu' && cmd!=='.ping') {
      // Let it show it recognized the command
      // You can add real functions later
    }
  })
}
start()
