// Description: Java 25 Table Object implementation for IndexTweak.

/*
 *	server.markhome.mcf.CFBam
 *
 *	Copyright (c) 2016-2026 Mark Stephen Sobkow
 *	
 *	Mark's Code Fractal CFBam 3.1 Business Application Model
 *	
 *	Copyright 2016-2026 Mark Stephen Sobkow
 *	
 *	This file is part of Mark's Code Fractal CFBam.
 *	
 *	Mark's Code Fractal CFBam is available under dual commercial license from
 *	Mark Stephen Sobkow, or under the terms of the GNU General Public License,
 *	Version 3 or later with classpath and static linking exceptions.
 *	
 *	As a special exception, Mark Sobkow gives you permission to link this library
 *	with independent modules to produce an executable, provided that none of them
 *	conflict with the intent of the GPLv3; that is, you are not allowed to invoke
 *	the methods of this library from non-GPLv3-compatibly licensed code. You may not
 *	implement an LPGLv3 "wedge" to try to bypass this restriction. That said, code which
 *	does not rely on this library is free to specify whatever license its authors decide
 *	to use. Mark Sobkow specifically rejects the infectious nature of the GPLv3, and
 *	considers the mere act of including GPLv3 modules in an executable to be perfectly
 *	reasonable given tools like modern Java's single-jar deployment options.
 *	
 *	Mark's Code Fractal CFBam is free software: you can redistribute it and/or
 *	modify it under the terms of the GNU General Public License as published by
 *	the Free Software Foundation, either version 3 of the License, or
 *	(at your option) any later version.
 *	
 *	Mark's Code Fractal CFBam is distributed in the hope that it will be useful,
 *	but WITHOUT ANY WARRANTY; without even the implied warranty of
 *	MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *	GNU General Public License for more details.
 *	
 *	You should have received a copy of the GNU General Public License
 *	along with Mark's Code Fractal CFBam.  If not, see <https://www.gnu.org/licenses/>.
 *	
 *	If you wish to modify and use this code without publishing your changes,
 *	or integrate it with proprietary code, please contact Mark Stephen Sobkow
 *	for a commercial license at mark.sobkow@gmail.com
 */

package server.markhome.mcf.v3_1.cfbam.cfbamobj;

import java.math.*;
import java.sql.*;
import java.text.*;
import java.time.*;
import java.util.*;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.text.StringEscapeUtils;
import server.markhome.mcf.v3_1.cflib.*;
import server.markhome.mcf.v3_1.cflib.dbutil.*;
import server.markhome.mcf.v3_1.cfsec.cfsec.*;
import server.markhome.mcf.v3_1.cfint.cfint.*;
import server.markhome.mcf.v3_1.cfsec.cfsecobj.*;
import server.markhome.mcf.v3_1.cfint.cfintobj.*;
import server.markhome.mcf.v3_1.cfbam.cfbam.*;

public class CFBamIndexTweakTableObj
	implements ICFBamIndexTweakTableObj
{
	protected ICFBamSchemaObj schema;
	protected static int runtimeClassCode = ICFBamIndexTweak.CLASS_CODE;
	protected static final int backingClassCode = ICFBamIndexTweak.CLASS_CODE;
	private Map<CFLibDbKeyHash256, ICFBamIndexTweakObj> members;
	private Map<CFLibDbKeyHash256, ICFBamIndexTweakObj> allIndexTweak;
	private Map< ICFBamTweakByUNameIdxKey,
		ICFBamIndexTweakObj > indexByUNameIdx;
	private Map< ICFBamTweakByValTentIdxKey,
		Map<CFLibDbKeyHash256, ICFBamIndexTweakObj > > indexByValTentIdx;
	private Map< ICFBamTweakByScopeIdxKey,
		Map<CFLibDbKeyHash256, ICFBamIndexTweakObj > > indexByScopeIdx;
	private Map< ICFBamTweakByDefSchemaIdxKey,
		Map<CFLibDbKeyHash256, ICFBamIndexTweakObj > > indexByDefSchemaIdx;
	private Map< ICFBamTweakByUDefIdxKey,
		ICFBamIndexTweakObj > indexByUDefIdx;
	private Map< ICFBamIndexTweakByIndexIdxKey,
		Map<CFLibDbKeyHash256, ICFBamIndexTweakObj > > indexByIndexIdx;
	public static String TABLE_NAME = "IndexTweak";
	public static String TABLE_DBNAME = "idxtwk";

	public CFBamIndexTweakTableObj() {
		schema = null;
		members = new HashMap<CFLibDbKeyHash256, ICFBamIndexTweakObj>();
		allIndexTweak = null;
		indexByUNameIdx = null;
		indexByValTentIdx = null;
		indexByScopeIdx = null;
		indexByDefSchemaIdx = null;
		indexByUDefIdx = null;
		indexByIndexIdx = null;
	}

	public CFBamIndexTweakTableObj( ICFBamSchemaObj argSchema ) {
		schema = (ICFBamSchemaObj)argSchema;
		members = new HashMap<CFLibDbKeyHash256, ICFBamIndexTweakObj>();
		allIndexTweak = null;
		indexByUNameIdx = null;
		indexByValTentIdx = null;
		indexByScopeIdx = null;
		indexByDefSchemaIdx = null;
		indexByUDefIdx = null;
		indexByIndexIdx = null;
	}
	
	/**
	 *	Get class code always returns the runtime class code for the objects, which is not stable until the application is done initializing and registering its objects.
	 *
	 *	@return runtime classcode
	 */ 
	@Override
	public int getClassCode() {
		return CFBamIndexTweakTableObj.getRuntimeClassCode();
	}	

	/**
	 *	Get the backing store schema's class code, which is hard-coded into the object hierarchy.
	 *
	 *	@return The hardcoded backing store class code for this object, which is only valid in that schema.
	 */
	public static int getBackingClassCode() {
		return( backingClassCode );
	}

	/**
	 *	Get the runtime class code for this table; this value is only stable after the application is fully initialized.
	 *
	 *	@return runtimeClassCode
	 */
	public static int getRuntimeClassCode() {
		return( runtimeClassCode );
	}

	/**
	 *	Set the runtime class code for this table; this is done only during application initialization by the SchemaObj's <tt>initClassCodes()</tt> static method,
	 *	which will only set the class codes once and never again.  Once set, the class codes are immutable within the application.
	 *	Application programmers should never invoke this method, so it has package access only.
	 *
	 *	@param	argNewClassCode	The runtime class code to be used by clients and integrated application logic to identify this table of this schema.
	 */
	static void setRuntimeClassCode(int argNewClassCode ) {
		if (argNewClassCode <= 0) {
			throw new CFLibArgumentUnderflowException(CFBamIndexTweakTableObj.class, "setRuntimeClassCode", 1, "argNewClassCode", argNewClassCode, 1);
		}
		runtimeClassCode = argNewClassCode;
	}

	@Override
	public ICFBamSchemaObj getSchema() {
		return( schema );
	}

	@Override
	public void setSchema( ICFBamSchemaObj value ) {
		schema = (ICFBamSchemaObj)value;
	}

	@Override
	public String getTableName() {
		return( TABLE_NAME );
	}

	@Override
	public String getTableDbName() {
		return( TABLE_DBNAME );
	}

	@Override
	public Class getObjQualifyingClass() {
		return( ICFBamSchemaDefObj.class );
	}


	@Override
	public void minimizeMemory() {
		allIndexTweak = null;
		indexByUNameIdx = null;
		indexByValTentIdx = null;
		indexByScopeIdx = null;
		indexByDefSchemaIdx = null;
		indexByUDefIdx = null;
		indexByIndexIdx = null;
		List<ICFBamIndexTweakObj> toForget = new LinkedList<ICFBamIndexTweakObj>();
		ICFBamIndexTweakObj cur = null;
		Iterator<ICFBamIndexTweakObj> iter = members.values().iterator();
		while( iter.hasNext() ) {
			cur = iter.next();
			toForget.add( cur );
		}
		iter = toForget.iterator();
		while( iter.hasNext() ) {
			cur = iter.next();
			cur.forget();
		}
	}
	/**
	 *	If your implementation subclasses the objects,
	 *	you'll want to overload the constructByClassCode()
	 *	implementation to return your implementation's
	 *	instances instead of the base implementation.
	 *
	 *	This is the sole factory for instances derived from
	 *	CFBamIndexTweakObj.
	 */
	@Override
	public ICFBamIndexTweakObj newInstance() {
		ICFBamIndexTweakObj inst = new CFBamIndexTweakObj( schema );
		return( inst );
	}

	/**
	 *	If your implementation subclasses the objects,
	 *	you'll want to overload the constructByClassCode()
	 *	implementation to return your implementation's
	 *	instances instead of the base implementation.
	 *
	 *	This is the sole factory for instances derived from
	 *	CFBamIndexTweakObj.
	 */
	@Override
	public ICFBamIndexTweakEditObj newEditInstance( ICFBamIndexTweakObj orig ) {
		ICFBamIndexTweakEditObj edit = new CFBamIndexTweakEditObj( orig );
		return( edit );
	}

	@Override
	public ICFBamIndexTweakObj realiseIndexTweak( ICFBamIndexTweakObj Obj ) {
		ICFBamIndexTweakObj obj = Obj;
		CFLibDbKeyHash256 pkey = obj.getPKey();
		ICFBamIndexTweakObj keepObj = null;
		if( members.containsKey( pkey ) && ( null != members.get( pkey ) ) ) {
			ICFBamIndexTweakObj existingObj = members.get( pkey );
			keepObj = existingObj;

			/*
			 *	We always rebind the data because if we're being called, some index has
			 *	been updated and is refreshing it's data, which may or may not have changed
			 */

			// Detach object from alternate and duplicate indexes, leave PKey alone

			if( indexByUNameIdx != null ) {
				ICFBamTweakByUNameIdxKey keyUNameIdx =
					schema.getCFBamBackingStore().getFactoryTweak().newByUNameIdxKey();
				keyUNameIdx.setRequiredScopeId( keepObj.getRequiredScopeId() );
				keyUNameIdx.setRequiredName( keepObj.getRequiredName() );
				indexByUNameIdx.remove( keyUNameIdx );
			}

			if( indexByValTentIdx != null ) {
				ICFBamTweakByValTentIdxKey keyValTentIdx =
					schema.getCFBamBackingStore().getFactoryTweak().newByValTentIdxKey();
				keyValTentIdx.setRequiredTenantId( keepObj.getRequiredTenantId() );
				Map<CFLibDbKeyHash256, ICFBamIndexTweakObj > mapValTentIdx = indexByValTentIdx.get( keyValTentIdx );
				if( mapValTentIdx != null ) {
					indexByValTentIdx.remove( keyValTentIdx );
				}
			}

			if( indexByScopeIdx != null ) {
				ICFBamTweakByScopeIdxKey keyScopeIdx =
					schema.getCFBamBackingStore().getFactoryTweak().newByScopeIdxKey();
				keyScopeIdx.setRequiredScopeId( keepObj.getRequiredScopeId() );
				Map<CFLibDbKeyHash256, ICFBamIndexTweakObj > mapScopeIdx = indexByScopeIdx.get( keyScopeIdx );
				if( mapScopeIdx != null ) {
					indexByScopeIdx.remove( keyScopeIdx );
				}
			}

			if( indexByDefSchemaIdx != null ) {
				ICFBamTweakByDefSchemaIdxKey keyDefSchemaIdx =
					schema.getCFBamBackingStore().getFactoryTweak().newByDefSchemaIdxKey();
				keyDefSchemaIdx.setOptionalDefSchemaId( keepObj.getOptionalDefSchemaId() );
				Map<CFLibDbKeyHash256, ICFBamIndexTweakObj > mapDefSchemaIdx = indexByDefSchemaIdx.get( keyDefSchemaIdx );
				if( mapDefSchemaIdx != null ) {
					indexByDefSchemaIdx.remove( keyDefSchemaIdx );
				}
			}

			if( indexByUDefIdx != null ) {
				ICFBamTweakByUDefIdxKey keyUDefIdx =
					schema.getCFBamBackingStore().getFactoryTweak().newByUDefIdxKey();
				keyUDefIdx.setRequiredTenantId( keepObj.getRequiredTenantId() );
				keyUDefIdx.setRequiredScopeId( keepObj.getRequiredScopeId() );
				keyUDefIdx.setOptionalDefSchemaTenantId( keepObj.getOptionalDefSchemaTenantId() );
				keyUDefIdx.setOptionalDefSchemaId( keepObj.getOptionalDefSchemaId() );
				keyUDefIdx.setRequiredName( keepObj.getRequiredName() );
				indexByUDefIdx.remove( keyUDefIdx );
			}

			if( indexByIndexIdx != null ) {
				ICFBamIndexTweakByIndexIdxKey keyIndexIdx =
					schema.getCFBamBackingStore().getFactoryIndexTweak().newByIndexIdxKey();
				keyIndexIdx.setRequiredIndexId( keepObj.getRequiredIndexId() );
				Map<CFLibDbKeyHash256, ICFBamIndexTweakObj > mapIndexIdx = indexByIndexIdx.get( keyIndexIdx );
				if( mapIndexIdx != null ) {
					mapIndexIdx.remove( keepObj.getPKey() );
					if( mapIndexIdx.size() <= 0 ) {
						indexByIndexIdx.remove( keyIndexIdx );
					}
				}
			}
			// Keep passing the new object because it's the one with the record
			// that the base table needs to copy to the existing object from
			// the cache.
			keepObj = (ICFBamIndexTweakObj)schema.getTweakTableObj().realiseTweak( Obj );

			// Attach new object to alternate and duplicate indexes -- PKey stay stable

			if( indexByUNameIdx != null ) {
				ICFBamTweakByUNameIdxKey keyUNameIdx =
					schema.getCFBamBackingStore().getFactoryTweak().newByUNameIdxKey();
				keyUNameIdx.setRequiredScopeId( keepObj.getRequiredScopeId() );
				keyUNameIdx.setRequiredName( keepObj.getRequiredName() );
				indexByUNameIdx.put( keyUNameIdx, keepObj );
			}

			if( indexByValTentIdx != null ) {
				ICFBamTweakByValTentIdxKey keyValTentIdx =
					schema.getCFBamBackingStore().getFactoryTweak().newByValTentIdxKey();
				keyValTentIdx.setRequiredTenantId( keepObj.getRequiredTenantId() );
				Map<CFLibDbKeyHash256, ICFBamIndexTweakObj > mapValTentIdx = indexByValTentIdx.get( keyValTentIdx );
				if( mapValTentIdx != null ) {
					mapValTentIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByScopeIdx != null ) {
				ICFBamTweakByScopeIdxKey keyScopeIdx =
					schema.getCFBamBackingStore().getFactoryTweak().newByScopeIdxKey();
				keyScopeIdx.setRequiredScopeId( keepObj.getRequiredScopeId() );
				Map<CFLibDbKeyHash256, ICFBamIndexTweakObj > mapScopeIdx = indexByScopeIdx.get( keyScopeIdx );
				if( mapScopeIdx != null ) {
					mapScopeIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByDefSchemaIdx != null ) {
				ICFBamTweakByDefSchemaIdxKey keyDefSchemaIdx =
					schema.getCFBamBackingStore().getFactoryTweak().newByDefSchemaIdxKey();
				keyDefSchemaIdx.setOptionalDefSchemaId( keepObj.getOptionalDefSchemaId() );
				Map<CFLibDbKeyHash256, ICFBamIndexTweakObj > mapDefSchemaIdx = indexByDefSchemaIdx.get( keyDefSchemaIdx );
				if( mapDefSchemaIdx != null ) {
					mapDefSchemaIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByUDefIdx != null ) {
				ICFBamTweakByUDefIdxKey keyUDefIdx =
					schema.getCFBamBackingStore().getFactoryTweak().newByUDefIdxKey();
				keyUDefIdx.setRequiredTenantId( keepObj.getRequiredTenantId() );
				keyUDefIdx.setRequiredScopeId( keepObj.getRequiredScopeId() );
				keyUDefIdx.setOptionalDefSchemaTenantId( keepObj.getOptionalDefSchemaTenantId() );
				keyUDefIdx.setOptionalDefSchemaId( keepObj.getOptionalDefSchemaId() );
				keyUDefIdx.setRequiredName( keepObj.getRequiredName() );
				indexByUDefIdx.put( keyUDefIdx, keepObj );
			}

			if( indexByIndexIdx != null ) {
				ICFBamIndexTweakByIndexIdxKey keyIndexIdx =
					schema.getCFBamBackingStore().getFactoryIndexTweak().newByIndexIdxKey();
				keyIndexIdx.setRequiredIndexId( keepObj.getRequiredIndexId() );
				Map<CFLibDbKeyHash256, ICFBamIndexTweakObj > mapIndexIdx = indexByIndexIdx.get( keyIndexIdx );
				if( mapIndexIdx != null ) {
					mapIndexIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( allIndexTweak != null ) {
				allIndexTweak.put( keepObj.getPKey(), keepObj );
			}
		}
		else {
			keepObj = obj;
			keepObj = (ICFBamIndexTweakObj)schema.getTweakTableObj().realiseTweak( keepObj );

			// Attach new object to PKey, all, alternate, and duplicate indexes
			members.put( keepObj.getPKey(), keepObj );
			if( allIndexTweak != null ) {
				allIndexTweak.put( keepObj.getPKey(), keepObj );
			}

			if( indexByUNameIdx != null ) {
				ICFBamTweakByUNameIdxKey keyUNameIdx =
					schema.getCFBamBackingStore().getFactoryTweak().newByUNameIdxKey();
				keyUNameIdx.setRequiredScopeId( keepObj.getRequiredScopeId() );
				keyUNameIdx.setRequiredName( keepObj.getRequiredName() );
				indexByUNameIdx.put( keyUNameIdx, keepObj );
			}

			if( indexByValTentIdx != null ) {
				ICFBamTweakByValTentIdxKey keyValTentIdx =
					schema.getCFBamBackingStore().getFactoryTweak().newByValTentIdxKey();
				keyValTentIdx.setRequiredTenantId( keepObj.getRequiredTenantId() );
				Map<CFLibDbKeyHash256, ICFBamIndexTweakObj > mapValTentIdx = indexByValTentIdx.get( keyValTentIdx );
				if( mapValTentIdx != null ) {
					mapValTentIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByScopeIdx != null ) {
				ICFBamTweakByScopeIdxKey keyScopeIdx =
					schema.getCFBamBackingStore().getFactoryTweak().newByScopeIdxKey();
				keyScopeIdx.setRequiredScopeId( keepObj.getRequiredScopeId() );
				Map<CFLibDbKeyHash256, ICFBamIndexTweakObj > mapScopeIdx = indexByScopeIdx.get( keyScopeIdx );
				if( mapScopeIdx != null ) {
					mapScopeIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByDefSchemaIdx != null ) {
				ICFBamTweakByDefSchemaIdxKey keyDefSchemaIdx =
					schema.getCFBamBackingStore().getFactoryTweak().newByDefSchemaIdxKey();
				keyDefSchemaIdx.setOptionalDefSchemaId( keepObj.getOptionalDefSchemaId() );
				Map<CFLibDbKeyHash256, ICFBamIndexTweakObj > mapDefSchemaIdx = indexByDefSchemaIdx.get( keyDefSchemaIdx );
				if( mapDefSchemaIdx != null ) {
					mapDefSchemaIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByUDefIdx != null ) {
				ICFBamTweakByUDefIdxKey keyUDefIdx =
					schema.getCFBamBackingStore().getFactoryTweak().newByUDefIdxKey();
				keyUDefIdx.setRequiredTenantId( keepObj.getRequiredTenantId() );
				keyUDefIdx.setRequiredScopeId( keepObj.getRequiredScopeId() );
				keyUDefIdx.setOptionalDefSchemaTenantId( keepObj.getOptionalDefSchemaTenantId() );
				keyUDefIdx.setOptionalDefSchemaId( keepObj.getOptionalDefSchemaId() );
				keyUDefIdx.setRequiredName( keepObj.getRequiredName() );
				indexByUDefIdx.put( keyUDefIdx, keepObj );
			}

			if( indexByIndexIdx != null ) {
				ICFBamIndexTweakByIndexIdxKey keyIndexIdx =
					schema.getCFBamBackingStore().getFactoryIndexTweak().newByIndexIdxKey();
				keyIndexIdx.setRequiredIndexId( keepObj.getRequiredIndexId() );
				Map<CFLibDbKeyHash256, ICFBamIndexTweakObj > mapIndexIdx = indexByIndexIdx.get( keyIndexIdx );
				if( mapIndexIdx != null ) {
					mapIndexIdx.put( keepObj.getPKey(), keepObj );
				}
			}

		}
		return( keepObj );
	}

	@Override
	public ICFBamIndexTweakObj createIndexTweak( ICFBamIndexTweakObj Obj ) {
		ICFBamIndexTweakObj obj = Obj;
		ICFBamIndexTweak rec = obj.getIndexTweakRec();
		schema.getCFBamBackingStore().getTableIndexTweak().createIndexTweak(
			null,
			rec );
		obj.copyRecToPKey();
		if( obj.getClassCode() == runtimeClassCode ) {
			obj = (ICFBamIndexTweakObj)(obj.realise());
		}
		obj.endEdit();
		return( obj );
	}

	@Override
	public ICFBamIndexTweakObj readIndexTweak( CFLibDbKeyHash256 pkey ) {
		return( readIndexTweak( pkey, false ) );
	}

	@Override
	public ICFBamIndexTweakObj readIndexTweak( CFLibDbKeyHash256 pkey, boolean forceRead ) {
		ICFBamIndexTweakObj obj = null;
		if( ( ! forceRead ) && members.containsKey( pkey ) ) {
			obj = members.get( pkey );
		}
		else {
			ICFBamIndexTweak readRec = schema.getCFBamBackingStore().getTableIndexTweak().readDerivedByIdIdx( null,
						pkey );
			if( readRec != null ) {
				obj = (ICFBamIndexTweakObj)schema.getTweakTableObj().constructByClassCode( readRec.getClassCode() );
				obj.setPKey( readRec.getPKey() );
				obj.setRec( readRec );
				obj = (ICFBamIndexTweakObj)obj.realise();
			}
		}
		return( obj );
	}

	@Override
	public ICFBamIndexTweakObj readCachedIndexTweak( CFLibDbKeyHash256 pkey ) {
		ICFBamIndexTweakObj obj = null;
		if( members.containsKey( pkey ) ) {
			obj = members.get( pkey );
		}
		return( obj );
	}

	@Override
	public void reallyDeepDisposeIndexTweak( ICFBamIndexTweakObj obj )
	{
		final String S_ProcName = "CFBamIndexTweakTableObj.reallyDeepDisposeIndexTweak() ";
		String classCode;
		if( obj == null ) {
			return;
		}
		CFLibDbKeyHash256 pkey = obj.getPKey();
		ICFBamIndexTweakObj existing = readCachedIndexTweak( pkey );
		if( existing == null ) {
			return;
		}
		members.remove( pkey );
		ICFBamIndexTweakByIndexIdxKey keyIndexIdx = schema.getCFBamBackingStore().getFactoryIndexTweak().newByIndexIdxKey();
		keyIndexIdx.setRequiredIndexId( existing.getRequiredIndexId() );



		if( indexByIndexIdx != null ) {
			if( indexByIndexIdx.containsKey( keyIndexIdx ) ) {
				indexByIndexIdx.get( keyIndexIdx ).remove( pkey );
				if( indexByIndexIdx.get( keyIndexIdx ).size() <= 0 ) {
					indexByIndexIdx.remove( keyIndexIdx );
				}
			}
		}


		schema.getTweakTableObj().reallyDeepDisposeTweak( obj );
	}
	@Override
	public void deepDisposeIndexTweak( CFLibDbKeyHash256 pkey ) {
		ICFBamIndexTweakObj obj = readCachedIndexTweak( pkey );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public ICFBamIndexTweakObj lockIndexTweak( CFLibDbKeyHash256 pkey ) {
		ICFBamIndexTweakObj locked = null;
		ICFBamIndexTweak lockRec = schema.getCFBamBackingStore().getTableIndexTweak().lockDerived( null, pkey );
		if( lockRec != null ) {
				locked = (ICFBamIndexTweakObj)schema.getTweakTableObj().constructByClassCode( lockRec.getClassCode() );
			locked.setRec( lockRec );
			locked.setPKey( lockRec.getPKey() );
			locked = (ICFBamIndexTweakObj)locked.realise();
		}
		else {
			throw new CFLibCollisionDetectedException( getClass(), "lockIndexTweak", pkey );
		}
		return( locked );
	}

	@Override
	public List<ICFBamIndexTweakObj> readAllIndexTweak() {
		return( readAllIndexTweak( false ) );
	}

	@Override
	public List<ICFBamIndexTweakObj> readAllIndexTweak( boolean forceRead ) {
		final String S_ProcName = "readAllIndexTweak";
		if( ( allIndexTweak == null ) || forceRead ) {
			Map<CFLibDbKeyHash256, ICFBamIndexTweakObj> map = new HashMap<CFLibDbKeyHash256,ICFBamIndexTweakObj>();
			allIndexTweak = map;
			ICFBamIndexTweak[] recList = schema.getCFBamBackingStore().getTableIndexTweak().readAllDerived( null );
			ICFBamIndexTweak rec;
			ICFBamIndexTweakObj obj;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamIndexTweakObj)schema.getTweakTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamIndexTweakObj realised = (ICFBamIndexTweakObj)obj.realise();
			}
		}
		int len = allIndexTweak.size();
		ICFBamIndexTweakObj arr[] = new ICFBamIndexTweakObj[len];
		Iterator<ICFBamIndexTweakObj> valIter = allIndexTweak.values().iterator();
		int idx = 0;
		while( ( idx < len ) && valIter.hasNext() ) {
			arr[idx++] = valIter.next();
		}
		if( idx < len ) {
			throw new CFLibArgumentUnderflowException( getClass(),
				S_ProcName,
				0,
				"idx",
				idx,
				len );
		}
		else if( valIter.hasNext() ) {
			throw new CFLibArgumentOverflowException( getClass(),
					S_ProcName,
					0,
					"idx",
					idx,
					len );
		}
		ArrayList<ICFBamIndexTweakObj> arrayList = new ArrayList<ICFBamIndexTweakObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamIndexTweakObj> cmp = new Comparator<ICFBamIndexTweakObj>() {
			@Override
			public int compare( ICFBamIndexTweakObj lhs, ICFBamIndexTweakObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		List<ICFBamIndexTweakObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFBamIndexTweakObj> readCachedAllIndexTweak() {
		final String S_ProcName = "readCachedAllIndexTweak";
		ArrayList<ICFBamIndexTweakObj> arrayList = new ArrayList<ICFBamIndexTweakObj>();
		if( allIndexTweak != null ) {
			int len = allIndexTweak.size();
			ICFBamIndexTweakObj arr[] = new ICFBamIndexTweakObj[len];
			Iterator<ICFBamIndexTweakObj> valIter = allIndexTweak.values().iterator();
			int idx = 0;
			while( ( idx < len ) && valIter.hasNext() ) {
				arr[idx++] = valIter.next();
			}
			if( idx < len ) {
				throw new CFLibArgumentUnderflowException( getClass(),
					S_ProcName,
					0,
					"idx",
					idx,
					len );
			}
			else if( valIter.hasNext() ) {
				throw new CFLibArgumentOverflowException( getClass(),
						S_ProcName,
						0,
						"idx",
						idx,
						len );
			}
			for( idx = 0; idx < len; idx ++ ) {
				arrayList.add( arr[idx] );
			}
		}
		Comparator<ICFBamIndexTweakObj> cmp = new Comparator<ICFBamIndexTweakObj>() {
			public int compare( ICFBamIndexTweakObj lhs, ICFBamIndexTweakObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		return( arrayList );
	}

	@Override
	public ICFBamIndexTweakObj readIndexTweakByIdIdx( CFLibDbKeyHash256 Id )
	{
		return( readIndexTweakByIdIdx( Id,
			false ) );
	}

	@Override
	public ICFBamIndexTweakObj readIndexTweakByIdIdx( CFLibDbKeyHash256 Id, boolean forceRead )
	{
		ICFBamIndexTweakObj obj = readIndexTweak( Id, forceRead );
		return( obj );
	}

	@Override
	public ICFBamIndexTweakObj readIndexTweakByUNameIdx( CFLibDbKeyHash256 ScopeId,
		String Name )
	{
		return( readIndexTweakByUNameIdx( ScopeId,
			Name,
			false ) );
	}

	@Override
	public ICFBamIndexTweakObj readIndexTweakByUNameIdx( CFLibDbKeyHash256 ScopeId,
		String Name, boolean forceRead )
	{
		if( indexByUNameIdx == null ) {
			indexByUNameIdx = new HashMap< ICFBamTweakByUNameIdxKey,
				ICFBamIndexTweakObj >();
		}
		ICFBamTweakByUNameIdxKey key = schema.getCFBamBackingStore().getFactoryTweak().newByUNameIdxKey();
		key.setRequiredScopeId( ScopeId );
		key.setRequiredName( Name );
		ICFBamIndexTweakObj obj = null;
		if( ( ! forceRead ) && indexByUNameIdx.containsKey( key ) ) {
			obj = indexByUNameIdx.get( key );
		}
		else {
			ICFBamTweak rec = schema.getCFBamBackingStore().getTableTweak().readDerivedByUNameIdx( null,
				ScopeId,
				Name );
			if( rec != null ) {
				obj = (ICFBamIndexTweakObj)schema.getTweakTableObj().constructByClassCode( rec.getClassCode() );
				obj.setRec( rec );
				obj.setPKey( rec.getPKey() );
				obj = (ICFBamIndexTweakObj)obj.realise();
			}
		}
		return( obj );
	}

	@Override
	public List<ICFBamIndexTweakObj> readIndexTweakByValTentIdx( CFLibDbKeyHash256 TenantId )
	{
		return( readIndexTweakByValTentIdx( TenantId,
			false ) );
	}

	@Override
	public List<ICFBamIndexTweakObj> readIndexTweakByValTentIdx( CFLibDbKeyHash256 TenantId,
		boolean forceRead )
	{
		final String S_ProcName = "readIndexTweakByValTentIdx";
		ICFBamTweakByValTentIdxKey key = schema.getCFBamBackingStore().getFactoryTweak().newByValTentIdxKey();
		key.setRequiredTenantId( TenantId );
		Map<CFLibDbKeyHash256, ICFBamIndexTweakObj> dict;
		if( indexByValTentIdx == null ) {
			indexByValTentIdx = new HashMap< ICFBamTweakByValTentIdxKey,
				Map< CFLibDbKeyHash256, ICFBamIndexTweakObj > >();
		}
		if( ( ! forceRead ) && indexByValTentIdx.containsKey( key ) ) {
			dict = indexByValTentIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFBamIndexTweakObj>();
			ICFBamTweakObj obj;
			ICFBamTweak[] recList = schema.getCFBamBackingStore().getTableTweak().readDerivedByValTentIdx( null,
				TenantId );
			ICFBamTweak rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamIndexTweakObj)schema.getTweakTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamIndexTweakObj realised = (ICFBamIndexTweakObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByValTentIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamIndexTweakObj arr[] = new ICFBamIndexTweakObj[len];
		Iterator<ICFBamIndexTweakObj> valIter = dict.values().iterator();
		int idx = 0;
		while( ( idx < len ) && valIter.hasNext() ) {
			arr[idx++] = valIter.next();
		}
		if( idx < len ) {
			throw new CFLibArgumentUnderflowException( getClass(),
				S_ProcName,
				0,
				"idx",
				idx,
				len );
		}
		else if( valIter.hasNext() ) {
			throw new CFLibArgumentOverflowException( getClass(),
					S_ProcName,
					0,
					"idx",
					idx,
					len );
		}
		ArrayList<ICFBamIndexTweakObj> arrayList = new ArrayList<ICFBamIndexTweakObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamIndexTweakObj> cmp = new Comparator<ICFBamIndexTweakObj>() {
			@Override
			public int compare( ICFBamIndexTweakObj lhs, ICFBamIndexTweakObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		List<ICFBamIndexTweakObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFBamIndexTweakObj> readIndexTweakByScopeIdx( CFLibDbKeyHash256 ScopeId )
	{
		return( readIndexTweakByScopeIdx( ScopeId,
			false ) );
	}

	@Override
	public List<ICFBamIndexTweakObj> readIndexTweakByScopeIdx( CFLibDbKeyHash256 ScopeId,
		boolean forceRead )
	{
		final String S_ProcName = "readIndexTweakByScopeIdx";
		ICFBamTweakByScopeIdxKey key = schema.getCFBamBackingStore().getFactoryTweak().newByScopeIdxKey();
		key.setRequiredScopeId( ScopeId );
		Map<CFLibDbKeyHash256, ICFBamIndexTweakObj> dict;
		if( indexByScopeIdx == null ) {
			indexByScopeIdx = new HashMap< ICFBamTweakByScopeIdxKey,
				Map< CFLibDbKeyHash256, ICFBamIndexTweakObj > >();
		}
		if( ( ! forceRead ) && indexByScopeIdx.containsKey( key ) ) {
			dict = indexByScopeIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFBamIndexTweakObj>();
			ICFBamTweakObj obj;
			ICFBamTweak[] recList = schema.getCFBamBackingStore().getTableTweak().readDerivedByScopeIdx( null,
				ScopeId );
			ICFBamTweak rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamIndexTweakObj)schema.getTweakTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamIndexTweakObj realised = (ICFBamIndexTweakObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByScopeIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamIndexTweakObj arr[] = new ICFBamIndexTweakObj[len];
		Iterator<ICFBamIndexTweakObj> valIter = dict.values().iterator();
		int idx = 0;
		while( ( idx < len ) && valIter.hasNext() ) {
			arr[idx++] = valIter.next();
		}
		if( idx < len ) {
			throw new CFLibArgumentUnderflowException( getClass(),
				S_ProcName,
				0,
				"idx",
				idx,
				len );
		}
		else if( valIter.hasNext() ) {
			throw new CFLibArgumentOverflowException( getClass(),
					S_ProcName,
					0,
					"idx",
					idx,
					len );
		}
		ArrayList<ICFBamIndexTweakObj> arrayList = new ArrayList<ICFBamIndexTweakObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamIndexTweakObj> cmp = new Comparator<ICFBamIndexTweakObj>() {
			@Override
			public int compare( ICFBamIndexTweakObj lhs, ICFBamIndexTweakObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		List<ICFBamIndexTweakObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFBamIndexTweakObj> readIndexTweakByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId )
	{
		return( readIndexTweakByDefSchemaIdx( DefSchemaId,
			false ) );
	}

	@Override
	public List<ICFBamIndexTweakObj> readIndexTweakByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId,
		boolean forceRead )
	{
		final String S_ProcName = "readIndexTweakByDefSchemaIdx";
		ICFBamTweakByDefSchemaIdxKey key = schema.getCFBamBackingStore().getFactoryTweak().newByDefSchemaIdxKey();
		key.setOptionalDefSchemaId( DefSchemaId );
		Map<CFLibDbKeyHash256, ICFBamIndexTweakObj> dict;
		if( indexByDefSchemaIdx == null ) {
			indexByDefSchemaIdx = new HashMap< ICFBamTweakByDefSchemaIdxKey,
				Map< CFLibDbKeyHash256, ICFBamIndexTweakObj > >();
		}
		if( ( ! forceRead ) && indexByDefSchemaIdx.containsKey( key ) ) {
			dict = indexByDefSchemaIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFBamIndexTweakObj>();
			ICFBamTweakObj obj;
			ICFBamTweak[] recList = schema.getCFBamBackingStore().getTableTweak().readDerivedByDefSchemaIdx( null,
				DefSchemaId );
			ICFBamTweak rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamIndexTweakObj)schema.getTweakTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamIndexTweakObj realised = (ICFBamIndexTweakObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByDefSchemaIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamIndexTweakObj arr[] = new ICFBamIndexTweakObj[len];
		Iterator<ICFBamIndexTweakObj> valIter = dict.values().iterator();
		int idx = 0;
		while( ( idx < len ) && valIter.hasNext() ) {
			arr[idx++] = valIter.next();
		}
		if( idx < len ) {
			throw new CFLibArgumentUnderflowException( getClass(),
				S_ProcName,
				0,
				"idx",
				idx,
				len );
		}
		else if( valIter.hasNext() ) {
			throw new CFLibArgumentOverflowException( getClass(),
					S_ProcName,
					0,
					"idx",
					idx,
					len );
		}
		ArrayList<ICFBamIndexTweakObj> arrayList = new ArrayList<ICFBamIndexTweakObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamIndexTweakObj> cmp = new Comparator<ICFBamIndexTweakObj>() {
			@Override
			public int compare( ICFBamIndexTweakObj lhs, ICFBamIndexTweakObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		List<ICFBamIndexTweakObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public ICFBamIndexTweakObj readIndexTweakByUDefIdx( CFLibDbKeyHash256 TenantId,
		CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 DefSchemaTenantId,
		CFLibDbKeyHash256 DefSchemaId,
		String Name )
	{
		return( readIndexTweakByUDefIdx( TenantId,
			ScopeId,
			DefSchemaTenantId,
			DefSchemaId,
			Name,
			false ) );
	}

	@Override
	public ICFBamIndexTweakObj readIndexTweakByUDefIdx( CFLibDbKeyHash256 TenantId,
		CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 DefSchemaTenantId,
		CFLibDbKeyHash256 DefSchemaId,
		String Name, boolean forceRead )
	{
		if( indexByUDefIdx == null ) {
			indexByUDefIdx = new HashMap< ICFBamTweakByUDefIdxKey,
				ICFBamIndexTweakObj >();
		}
		ICFBamTweakByUDefIdxKey key = schema.getCFBamBackingStore().getFactoryTweak().newByUDefIdxKey();
		key.setRequiredTenantId( TenantId );
		key.setRequiredScopeId( ScopeId );
		key.setOptionalDefSchemaTenantId( DefSchemaTenantId );
		key.setOptionalDefSchemaId( DefSchemaId );
		key.setRequiredName( Name );
		ICFBamIndexTweakObj obj = null;
		if( ( ! forceRead ) && indexByUDefIdx.containsKey( key ) ) {
			obj = indexByUDefIdx.get( key );
		}
		else {
			ICFBamTweak rec = schema.getCFBamBackingStore().getTableTweak().readDerivedByUDefIdx( null,
				TenantId,
				ScopeId,
				DefSchemaTenantId,
				DefSchemaId,
				Name );
			if( rec != null ) {
				obj = (ICFBamIndexTweakObj)schema.getTweakTableObj().constructByClassCode( rec.getClassCode() );
				obj.setRec( rec );
				obj.setPKey( rec.getPKey() );
				obj = (ICFBamIndexTweakObj)obj.realise();
			}
		}
		return( obj );
	}

	@Override
	public List<ICFBamIndexTweakObj> readIndexTweakByIndexIdx( CFLibDbKeyHash256 IndexId )
	{
		return( readIndexTweakByIndexIdx( IndexId,
			false ) );
	}

	@Override
	public List<ICFBamIndexTweakObj> readIndexTweakByIndexIdx( CFLibDbKeyHash256 IndexId,
		boolean forceRead )
	{
		final String S_ProcName = "readIndexTweakByIndexIdx";
		ICFBamIndexTweakByIndexIdxKey key = schema.getCFBamBackingStore().getFactoryIndexTweak().newByIndexIdxKey();
		key.setRequiredIndexId( IndexId );
		Map<CFLibDbKeyHash256, ICFBamIndexTweakObj> dict;
		if( indexByIndexIdx == null ) {
			indexByIndexIdx = new HashMap< ICFBamIndexTweakByIndexIdxKey,
				Map< CFLibDbKeyHash256, ICFBamIndexTweakObj > >();
		}
		if( ( ! forceRead ) && indexByIndexIdx.containsKey( key ) ) {
			dict = indexByIndexIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFBamIndexTweakObj>();
			ICFBamIndexTweakObj obj;
			ICFBamIndexTweak[] recList = schema.getCFBamBackingStore().getTableIndexTweak().readDerivedByIndexIdx( null,
				IndexId );
			ICFBamIndexTweak rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamIndexTweakObj)schema.getTweakTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamIndexTweakObj realised = (ICFBamIndexTweakObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByIndexIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamIndexTweakObj arr[] = new ICFBamIndexTweakObj[len];
		Iterator<ICFBamIndexTweakObj> valIter = dict.values().iterator();
		int idx = 0;
		while( ( idx < len ) && valIter.hasNext() ) {
			arr[idx++] = valIter.next();
		}
		if( idx < len ) {
			throw new CFLibArgumentUnderflowException( getClass(),
				S_ProcName,
				0,
				"idx",
				idx,
				len );
		}
		else if( valIter.hasNext() ) {
			throw new CFLibArgumentOverflowException( getClass(),
					S_ProcName,
					0,
					"idx",
					idx,
					len );
		}
		ArrayList<ICFBamIndexTweakObj> arrayList = new ArrayList<ICFBamIndexTweakObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamIndexTweakObj> cmp = new Comparator<ICFBamIndexTweakObj>() {
			@Override
			public int compare( ICFBamIndexTweakObj lhs, ICFBamIndexTweakObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		List<ICFBamIndexTweakObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public ICFBamIndexTweakObj readCachedIndexTweakByIdIdx( CFLibDbKeyHash256 Id )
	{
		ICFBamIndexTweakObj obj = null;
		obj = readCachedIndexTweak( Id );
		return( obj );
	}

	@Override
	public ICFBamIndexTweakObj readCachedIndexTweakByUNameIdx( CFLibDbKeyHash256 ScopeId,
		String Name )
	{
		ICFBamIndexTweakObj obj = null;
		ICFBamTweakByUNameIdxKey key = schema.getCFBamBackingStore().getFactoryTweak().newByUNameIdxKey();
		key.setRequiredScopeId( ScopeId );
		key.setRequiredName( Name );
		if( indexByUNameIdx != null ) {
			if( indexByUNameIdx.containsKey( key ) ) {
				obj = indexByUNameIdx.get( key );
			}
			else {
				Iterator<ICFBamIndexTweakObj> valIter = members.values().iterator();
				while( ( obj == null ) && valIter.hasNext() ) {
					obj = valIter.next();
					if( obj != null ) {
						if( obj.getRec().compareTo( key ) != 0 ) {
							obj = null;
						}
					}
				}
			}
		}
		else {
			Iterator<ICFBamIndexTweakObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) != 0 ) {
						obj = null;
					}
				}
			}
		}
		return( obj );
	}

	@Override
	public List<ICFBamIndexTweakObj> readCachedIndexTweakByValTentIdx( CFLibDbKeyHash256 TenantId )
	{
		final String S_ProcName = "readCachedIndexTweakByValTentIdx";
		ICFBamTweakByValTentIdxKey key = schema.getCFBamBackingStore().getFactoryTweak().newByValTentIdxKey();
		key.setRequiredTenantId( TenantId );
		ArrayList<ICFBamIndexTweakObj> arrayList = new ArrayList<ICFBamIndexTweakObj>();
		if( indexByValTentIdx != null ) {
			Map<CFLibDbKeyHash256, ICFBamIndexTweakObj> dict;
			if( indexByValTentIdx.containsKey( key ) ) {
				dict = indexByValTentIdx.get( key );
				int len = dict.size();
				ICFBamIndexTweakObj arr[] = new ICFBamIndexTweakObj[len];
				Iterator<ICFBamIndexTweakObj> valIter = dict.values().iterator();
				int idx = 0;
				while( ( idx < len ) && valIter.hasNext() ) {
					arr[idx++] = valIter.next();
				}
				if( idx < len ) {
					throw new CFLibArgumentUnderflowException( getClass(),
						S_ProcName,
						0,
						"idx",
						idx,
						len );
				}
				else if( valIter.hasNext() ) {
					throw new CFLibArgumentOverflowException( getClass(),
							S_ProcName,
							0,
							"idx",
							idx,
							len );
				}
				for( idx = 0; idx < len; idx ++ ) {
					arrayList.add( arr[idx] );
				}
			}
		}
		else {
			ICFBamIndexTweakObj obj;
			Iterator<ICFBamIndexTweakObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamIndexTweakObj> cmp = new Comparator<ICFBamIndexTweakObj>() {
			@Override
			public int compare( ICFBamIndexTweakObj lhs, ICFBamIndexTweakObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		return( arrayList );
	}

	@Override
	public List<ICFBamIndexTweakObj> readCachedIndexTweakByScopeIdx( CFLibDbKeyHash256 ScopeId )
	{
		final String S_ProcName = "readCachedIndexTweakByScopeIdx";
		ICFBamTweakByScopeIdxKey key = schema.getCFBamBackingStore().getFactoryTweak().newByScopeIdxKey();
		key.setRequiredScopeId( ScopeId );
		ArrayList<ICFBamIndexTweakObj> arrayList = new ArrayList<ICFBamIndexTweakObj>();
		if( indexByScopeIdx != null ) {
			Map<CFLibDbKeyHash256, ICFBamIndexTweakObj> dict;
			if( indexByScopeIdx.containsKey( key ) ) {
				dict = indexByScopeIdx.get( key );
				int len = dict.size();
				ICFBamIndexTweakObj arr[] = new ICFBamIndexTweakObj[len];
				Iterator<ICFBamIndexTweakObj> valIter = dict.values().iterator();
				int idx = 0;
				while( ( idx < len ) && valIter.hasNext() ) {
					arr[idx++] = valIter.next();
				}
				if( idx < len ) {
					throw new CFLibArgumentUnderflowException( getClass(),
						S_ProcName,
						0,
						"idx",
						idx,
						len );
				}
				else if( valIter.hasNext() ) {
					throw new CFLibArgumentOverflowException( getClass(),
							S_ProcName,
							0,
							"idx",
							idx,
							len );
				}
				for( idx = 0; idx < len; idx ++ ) {
					arrayList.add( arr[idx] );
				}
			}
		}
		else {
			ICFBamIndexTweakObj obj;
			Iterator<ICFBamIndexTweakObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamIndexTweakObj> cmp = new Comparator<ICFBamIndexTweakObj>() {
			@Override
			public int compare( ICFBamIndexTweakObj lhs, ICFBamIndexTweakObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		return( arrayList );
	}

	@Override
	public List<ICFBamIndexTweakObj> readCachedIndexTweakByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId )
	{
		final String S_ProcName = "readCachedIndexTweakByDefSchemaIdx";
		ICFBamTweakByDefSchemaIdxKey key = schema.getCFBamBackingStore().getFactoryTweak().newByDefSchemaIdxKey();
		key.setOptionalDefSchemaId( DefSchemaId );
		ArrayList<ICFBamIndexTweakObj> arrayList = new ArrayList<ICFBamIndexTweakObj>();
		if( indexByDefSchemaIdx != null ) {
			Map<CFLibDbKeyHash256, ICFBamIndexTweakObj> dict;
			if( indexByDefSchemaIdx.containsKey( key ) ) {
				dict = indexByDefSchemaIdx.get( key );
				int len = dict.size();
				ICFBamIndexTweakObj arr[] = new ICFBamIndexTweakObj[len];
				Iterator<ICFBamIndexTweakObj> valIter = dict.values().iterator();
				int idx = 0;
				while( ( idx < len ) && valIter.hasNext() ) {
					arr[idx++] = valIter.next();
				}
				if( idx < len ) {
					throw new CFLibArgumentUnderflowException( getClass(),
						S_ProcName,
						0,
						"idx",
						idx,
						len );
				}
				else if( valIter.hasNext() ) {
					throw new CFLibArgumentOverflowException( getClass(),
							S_ProcName,
							0,
							"idx",
							idx,
							len );
				}
				for( idx = 0; idx < len; idx ++ ) {
					arrayList.add( arr[idx] );
				}
			}
		}
		else {
			ICFBamIndexTweakObj obj;
			Iterator<ICFBamIndexTweakObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamIndexTweakObj> cmp = new Comparator<ICFBamIndexTweakObj>() {
			@Override
			public int compare( ICFBamIndexTweakObj lhs, ICFBamIndexTweakObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		return( arrayList );
	}

	@Override
	public ICFBamIndexTweakObj readCachedIndexTweakByUDefIdx( CFLibDbKeyHash256 TenantId,
		CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 DefSchemaTenantId,
		CFLibDbKeyHash256 DefSchemaId,
		String Name )
	{
		ICFBamIndexTweakObj obj = null;
		ICFBamTweakByUDefIdxKey key = schema.getCFBamBackingStore().getFactoryTweak().newByUDefIdxKey();
		key.setRequiredTenantId( TenantId );
		key.setRequiredScopeId( ScopeId );
		key.setOptionalDefSchemaTenantId( DefSchemaTenantId );
		key.setOptionalDefSchemaId( DefSchemaId );
		key.setRequiredName( Name );
		if( indexByUDefIdx != null ) {
			if( indexByUDefIdx.containsKey( key ) ) {
				obj = indexByUDefIdx.get( key );
			}
			else {
				Iterator<ICFBamIndexTweakObj> valIter = members.values().iterator();
				while( ( obj == null ) && valIter.hasNext() ) {
					obj = valIter.next();
					if( obj != null ) {
						if( obj.getRec().compareTo( key ) != 0 ) {
							obj = null;
						}
					}
				}
			}
		}
		else {
			Iterator<ICFBamIndexTweakObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) != 0 ) {
						obj = null;
					}
				}
			}
		}
		return( obj );
	}

	@Override
	public List<ICFBamIndexTweakObj> readCachedIndexTweakByIndexIdx( CFLibDbKeyHash256 IndexId )
	{
		final String S_ProcName = "readCachedIndexTweakByIndexIdx";
		ICFBamIndexTweakByIndexIdxKey key = schema.getCFBamBackingStore().getFactoryIndexTweak().newByIndexIdxKey();
		key.setRequiredIndexId( IndexId );
		ArrayList<ICFBamIndexTweakObj> arrayList = new ArrayList<ICFBamIndexTweakObj>();
		if( indexByIndexIdx != null ) {
			Map<CFLibDbKeyHash256, ICFBamIndexTweakObj> dict;
			if( indexByIndexIdx.containsKey( key ) ) {
				dict = indexByIndexIdx.get( key );
				int len = dict.size();
				ICFBamIndexTweakObj arr[] = new ICFBamIndexTweakObj[len];
				Iterator<ICFBamIndexTweakObj> valIter = dict.values().iterator();
				int idx = 0;
				while( ( idx < len ) && valIter.hasNext() ) {
					arr[idx++] = valIter.next();
				}
				if( idx < len ) {
					throw new CFLibArgumentUnderflowException( getClass(),
						S_ProcName,
						0,
						"idx",
						idx,
						len );
				}
				else if( valIter.hasNext() ) {
					throw new CFLibArgumentOverflowException( getClass(),
							S_ProcName,
							0,
							"idx",
							idx,
							len );
				}
				for( idx = 0; idx < len; idx ++ ) {
					arrayList.add( arr[idx] );
				}
			}
		}
		else {
			ICFBamIndexTweakObj obj;
			Iterator<ICFBamIndexTweakObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamIndexTweakObj> cmp = new Comparator<ICFBamIndexTweakObj>() {
			@Override
			public int compare( ICFBamIndexTweakObj lhs, ICFBamIndexTweakObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		return( arrayList );
	}

	@Override
	public void deepDisposeIndexTweakByIdIdx( CFLibDbKeyHash256 Id )
	{
		ICFBamIndexTweakObj obj = readCachedIndexTweakByIdIdx( Id );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public void deepDisposeIndexTweakByUNameIdx( CFLibDbKeyHash256 ScopeId,
		String Name )
	{
		ICFBamIndexTweakObj obj = readCachedIndexTweakByUNameIdx( ScopeId,
				Name );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public void deepDisposeIndexTweakByValTentIdx( CFLibDbKeyHash256 TenantId )
	{
		final String S_ProcName = "deepDisposeIndexTweakByValTentIdx";
		ICFBamIndexTweakObj obj;
		List<ICFBamIndexTweakObj> arrayList = readCachedIndexTweakByValTentIdx( TenantId );
		if( arrayList != null )  {
			Iterator<ICFBamIndexTweakObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposeIndexTweakByScopeIdx( CFLibDbKeyHash256 ScopeId )
	{
		final String S_ProcName = "deepDisposeIndexTweakByScopeIdx";
		ICFBamIndexTweakObj obj;
		List<ICFBamIndexTweakObj> arrayList = readCachedIndexTweakByScopeIdx( ScopeId );
		if( arrayList != null )  {
			Iterator<ICFBamIndexTweakObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposeIndexTweakByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId )
	{
		final String S_ProcName = "deepDisposeIndexTweakByDefSchemaIdx";
		ICFBamIndexTweakObj obj;
		List<ICFBamIndexTweakObj> arrayList = readCachedIndexTweakByDefSchemaIdx( DefSchemaId );
		if( arrayList != null )  {
			Iterator<ICFBamIndexTweakObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposeIndexTweakByUDefIdx( CFLibDbKeyHash256 TenantId,
		CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 DefSchemaTenantId,
		CFLibDbKeyHash256 DefSchemaId,
		String Name )
	{
		ICFBamIndexTweakObj obj = readCachedIndexTweakByUDefIdx( TenantId,
				ScopeId,
				DefSchemaTenantId,
				DefSchemaId,
				Name );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public void deepDisposeIndexTweakByIndexIdx( CFLibDbKeyHash256 IndexId )
	{
		final String S_ProcName = "deepDisposeIndexTweakByIndexIdx";
		ICFBamIndexTweakObj obj;
		List<ICFBamIndexTweakObj> arrayList = readCachedIndexTweakByIndexIdx( IndexId );
		if( arrayList != null )  {
			Iterator<ICFBamIndexTweakObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public ICFBamIndexTweakObj updateIndexTweak( ICFBamIndexTweakObj Obj ) {
		ICFBamIndexTweakObj obj = Obj;
		schema.getCFBamBackingStore().getTableIndexTweak().updateIndexTweak( null,
			Obj.getIndexTweakRec() );
		if( Obj.getClassCode() == ((ICFBamSchemaObj)getSchema()).getIndexTweakTableObj().getClassCode() ) {
			obj = (ICFBamIndexTweakObj)Obj.realise();
		}
		obj.endEdit();
		return( obj );
	}

	@Override
	public void deleteIndexTweak( ICFBamIndexTweakObj Obj ) {
		ICFBamIndexTweakObj obj = Obj;
		schema.getCFBamBackingStore().getTableIndexTweak().deleteIndexTweak( null,
			obj.getIndexTweakRec() );
		Obj.forget();
	}

	@Override
	public void deleteIndexTweakByIdIdx( CFLibDbKeyHash256 Id )
	{
		ICFBamIndexTweakObj obj = readIndexTweak(Id);
		if( obj != null ) {
			ICFBamIndexTweakEditObj editObj = (ICFBamIndexTweakEditObj)obj.getEdit();
			boolean editStarted;
			if( editObj == null ) {
				editObj = (ICFBamIndexTweakEditObj)obj.beginEdit();
				if( editObj != null ) {
					editStarted = true;
				}
				else {
					editStarted = false;
				}
			}
			else {
				editStarted = false;
			}
			if( editObj != null ) {
				editObj.deleteInstance();
				if( editStarted ) {
					editObj.endEdit();
				}
			}
			obj.forget();
		}
		deepDisposeIndexTweakByIdIdx( Id );
	}

	@Override
	public void deleteIndexTweakByUNameIdx( CFLibDbKeyHash256 ScopeId,
		String Name )
	{
		if( indexByUNameIdx == null ) {
			indexByUNameIdx = new HashMap< ICFBamTweakByUNameIdxKey,
				ICFBamIndexTweakObj >();
		}
		ICFBamTweakByUNameIdxKey key = schema.getCFBamBackingStore().getFactoryTweak().newByUNameIdxKey();
		key.setRequiredScopeId( ScopeId );
		key.setRequiredName( Name );
		ICFBamIndexTweakObj obj = null;
		if( indexByUNameIdx.containsKey( key ) ) {
			obj = indexByUNameIdx.get( key );
			schema.getCFBamBackingStore().getTableIndexTweak().deleteIndexTweakByUNameIdx( null,
				ScopeId,
				Name );
			obj.forget();
		}
		else {
			schema.getCFBamBackingStore().getTableIndexTweak().deleteIndexTweakByUNameIdx( null,
				ScopeId,
				Name );
		}
		deepDisposeIndexTweakByUNameIdx( ScopeId,
				Name );
	}

	@Override
	public void deleteIndexTweakByValTentIdx( CFLibDbKeyHash256 TenantId )
	{
		ICFBamTweakByValTentIdxKey key = schema.getCFBamBackingStore().getFactoryTweak().newByValTentIdxKey();
		key.setRequiredTenantId( TenantId );
		if( indexByValTentIdx == null ) {
			indexByValTentIdx = new HashMap< ICFBamTweakByValTentIdxKey,
				Map< CFLibDbKeyHash256, ICFBamIndexTweakObj > >();
		}
		if( indexByValTentIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFBamIndexTweakObj> dict = indexByValTentIdx.get( key );
			schema.getCFBamBackingStore().getTableIndexTweak().deleteIndexTweakByValTentIdx( null,
				TenantId );
			Iterator<ICFBamIndexTweakObj> iter = dict.values().iterator();
			ICFBamIndexTweakObj obj;
			List<ICFBamIndexTweakObj> toForget = new LinkedList<ICFBamIndexTweakObj>();
			while( iter.hasNext() ) {
				obj = iter.next();
				toForget.add( obj );
			}
			iter = toForget.iterator();
			while( iter.hasNext() ) {
				obj = iter.next();
				obj.forget();
			}
			indexByValTentIdx.remove( key );
		}
		else {
			schema.getCFBamBackingStore().getTableIndexTweak().deleteIndexTweakByValTentIdx( null,
				TenantId );
		}
		deepDisposeIndexTweakByValTentIdx( TenantId );
	}

	@Override
	public void deleteIndexTweakByScopeIdx( CFLibDbKeyHash256 ScopeId )
	{
		ICFBamTweakByScopeIdxKey key = schema.getCFBamBackingStore().getFactoryTweak().newByScopeIdxKey();
		key.setRequiredScopeId( ScopeId );
		if( indexByScopeIdx == null ) {
			indexByScopeIdx = new HashMap< ICFBamTweakByScopeIdxKey,
				Map< CFLibDbKeyHash256, ICFBamIndexTweakObj > >();
		}
		if( indexByScopeIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFBamIndexTweakObj> dict = indexByScopeIdx.get( key );
			schema.getCFBamBackingStore().getTableIndexTweak().deleteIndexTweakByScopeIdx( null,
				ScopeId );
			Iterator<ICFBamIndexTweakObj> iter = dict.values().iterator();
			ICFBamIndexTweakObj obj;
			List<ICFBamIndexTweakObj> toForget = new LinkedList<ICFBamIndexTweakObj>();
			while( iter.hasNext() ) {
				obj = iter.next();
				toForget.add( obj );
			}
			iter = toForget.iterator();
			while( iter.hasNext() ) {
				obj = iter.next();
				obj.forget();
			}
			indexByScopeIdx.remove( key );
		}
		else {
			schema.getCFBamBackingStore().getTableIndexTweak().deleteIndexTweakByScopeIdx( null,
				ScopeId );
		}
		deepDisposeIndexTweakByScopeIdx( ScopeId );
	}

	@Override
	public void deleteIndexTweakByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId )
	{
		ICFBamTweakByDefSchemaIdxKey key = schema.getCFBamBackingStore().getFactoryTweak().newByDefSchemaIdxKey();
		key.setOptionalDefSchemaId( DefSchemaId );
		if( indexByDefSchemaIdx == null ) {
			indexByDefSchemaIdx = new HashMap< ICFBamTweakByDefSchemaIdxKey,
				Map< CFLibDbKeyHash256, ICFBamIndexTweakObj > >();
		}
		if( indexByDefSchemaIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFBamIndexTweakObj> dict = indexByDefSchemaIdx.get( key );
			schema.getCFBamBackingStore().getTableIndexTweak().deleteIndexTweakByDefSchemaIdx( null,
				DefSchemaId );
			Iterator<ICFBamIndexTweakObj> iter = dict.values().iterator();
			ICFBamIndexTweakObj obj;
			List<ICFBamIndexTweakObj> toForget = new LinkedList<ICFBamIndexTweakObj>();
			while( iter.hasNext() ) {
				obj = iter.next();
				toForget.add( obj );
			}
			iter = toForget.iterator();
			while( iter.hasNext() ) {
				obj = iter.next();
				obj.forget();
			}
			indexByDefSchemaIdx.remove( key );
		}
		else {
			schema.getCFBamBackingStore().getTableIndexTweak().deleteIndexTweakByDefSchemaIdx( null,
				DefSchemaId );
		}
		deepDisposeIndexTweakByDefSchemaIdx( DefSchemaId );
	}

	@Override
	public void deleteIndexTweakByUDefIdx( CFLibDbKeyHash256 TenantId,
		CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 DefSchemaTenantId,
		CFLibDbKeyHash256 DefSchemaId,
		String Name )
	{
		if( indexByUDefIdx == null ) {
			indexByUDefIdx = new HashMap< ICFBamTweakByUDefIdxKey,
				ICFBamIndexTweakObj >();
		}
		ICFBamTweakByUDefIdxKey key = schema.getCFBamBackingStore().getFactoryTweak().newByUDefIdxKey();
		key.setRequiredTenantId( TenantId );
		key.setRequiredScopeId( ScopeId );
		key.setOptionalDefSchemaTenantId( DefSchemaTenantId );
		key.setOptionalDefSchemaId( DefSchemaId );
		key.setRequiredName( Name );
		ICFBamIndexTweakObj obj = null;
		if( indexByUDefIdx.containsKey( key ) ) {
			obj = indexByUDefIdx.get( key );
			schema.getCFBamBackingStore().getTableIndexTweak().deleteIndexTweakByUDefIdx( null,
				TenantId,
				ScopeId,
				DefSchemaTenantId,
				DefSchemaId,
				Name );
			obj.forget();
		}
		else {
			schema.getCFBamBackingStore().getTableIndexTweak().deleteIndexTweakByUDefIdx( null,
				TenantId,
				ScopeId,
				DefSchemaTenantId,
				DefSchemaId,
				Name );
		}
		deepDisposeIndexTweakByUDefIdx( TenantId,
				ScopeId,
				DefSchemaTenantId,
				DefSchemaId,
				Name );
	}

	@Override
	public void deleteIndexTweakByIndexIdx( CFLibDbKeyHash256 IndexId )
	{
		ICFBamIndexTweakByIndexIdxKey key = schema.getCFBamBackingStore().getFactoryIndexTweak().newByIndexIdxKey();
		key.setRequiredIndexId( IndexId );
		if( indexByIndexIdx == null ) {
			indexByIndexIdx = new HashMap< ICFBamIndexTweakByIndexIdxKey,
				Map< CFLibDbKeyHash256, ICFBamIndexTweakObj > >();
		}
		if( indexByIndexIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFBamIndexTweakObj> dict = indexByIndexIdx.get( key );
			schema.getCFBamBackingStore().getTableIndexTweak().deleteIndexTweakByIndexIdx( null,
				IndexId );
			Iterator<ICFBamIndexTweakObj> iter = dict.values().iterator();
			ICFBamIndexTweakObj obj;
			List<ICFBamIndexTweakObj> toForget = new LinkedList<ICFBamIndexTweakObj>();
			while( iter.hasNext() ) {
				obj = iter.next();
				toForget.add( obj );
			}
			iter = toForget.iterator();
			while( iter.hasNext() ) {
				obj = iter.next();
				obj.forget();
			}
			indexByIndexIdx.remove( key );
		}
		else {
			schema.getCFBamBackingStore().getTableIndexTweak().deleteIndexTweakByIndexIdx( null,
				IndexId );
		}
		deepDisposeIndexTweakByIndexIdx( IndexId );
	}
}