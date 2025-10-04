public class ParticleSpawnItem extends Item {

}
1 usage
    private static final ParticleEmitterInfo HERALD = new ParticleEmitterInfo(new Identifier( namespace: "lazer",
    path: "sample"));

1 usage
    public ParticleSpawnItem(Settings settings) f
super (settings);
1 usage
    @Override
    public ActionResult useOnBlock(ItemUsageContext context) f
    World world = context. getWorldO);
    BLockPos blockPos = context. getBlockPos();
if (world.isClientO){
        AAALevel.addParticle(world, force false, HERALD.Clone() position( x: blockPos-getX() + 0.5d, y: blockPos.getY) + 1d, z blockPos.getZ() + 0.5d));
        return super.use0nBlock(context);