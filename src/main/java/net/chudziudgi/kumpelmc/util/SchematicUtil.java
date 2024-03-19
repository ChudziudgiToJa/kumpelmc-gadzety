package net.chudziudgi.kumpelmc.util;

import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.WorldEditException;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.extent.clipboard.Clipboard;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormat;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormats;
import com.sk89q.worldedit.function.mask.*;
import com.sk89q.worldedit.function.operation.Operation;
import com.sk89q.worldedit.function.operation.Operations;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.session.ClipboardHolder;
import com.sk89q.worldedit.world.block.BlockType;
import com.sk89q.worldedit.world.block.BlockTypes;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.StandardOpenOption;
import java.util.Objects;

public class SchematicUtil {
    public static void pasteSchematic(Location location, String schematicName) throws IOException, WorldEditException {
        World world = location.getWorld();
        com.sk89q.worldedit.util.Location pasteLocation = BukkitAdapter.adapt(location.toCenterLocation());
        com.sk89q.worldedit.world.World pasteWorld = BukkitAdapter.adapt(world);
        EditSession editSession = WorldEdit.getInstance().newEditSession(pasteWorld);
        editSession.setMask(new BlockTypeMask(pasteLocation.getExtent(), BlockTypes.AIR.getDefaultState().getBlockType()));
        File schematicFile = new File("plugins/FastAsyncWorldEdit/schematics/" + schematicName);
        Clipboard clipboard = Objects.requireNonNull(ClipboardFormats.findByFile(schematicFile))
                .getReader(Files.newInputStream(schematicFile.toPath(), StandardOpenOption.READ)).read();
        ClipboardHolder clipboardHolder = new ClipboardHolder(clipboard);

        Operation build = clipboardHolder
                .createPaste(editSession)
                .to(BlockVector3.at(pasteLocation.getX(), pasteLocation.getY(), pasteLocation.getZ()))
                .ignoreAirBlocks(true)
                .build();

        Operations.complete(build);
        editSession.close();
    }
}
